SUMMARY = "Navigation application (with HMI) of the GENIVI navigation project"
DESCRIPTION = "Navigation application based on Navit and compliant with the \
Navigation APIs standardized by the GENIVI Alliance. The GENIVI APIs are \
implemented into Navit plugins, running on DBus. The HMI is made in QML."
HOMEPAGE = "http://projects.genivi.org/ivi-navigation"

PV = "1"

DEPENDS = "automotive-message-broker navit navigation-service qtbase qtdeclarative wayland-ivi-extension"
RDEPENDS_${PN} = "qtquickcontrols-qmlplugins"

SRC_URI = "git://github.com/GENIVI/navigation-application.git;protocol=http \
           file://remove_amb_link_path.patch \
           file://change_constants_path_ressourcejs.patch \
           file://constants.js \
          "
SRCREV = "9524c507b6def7757c9a506afb0a2ac0b4790802"

S = "${WORKDIR}/git"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2e0f5070190199ece7f69c3c14e2e7af"

inherit autotools cmake

EXTRA_CMAKEFLAGS = "-DDBUS_GENERATED_INCLUDE_DIR=${STAGING_INCDIR} -DTRIPCOMPUTER_DIR=${S}/src/tripcomputer"

PARALLEL_MAKE = ""

do_configure() {
    cd ${S}/src/genivilogreplayer && cmake ${EXTRA_CMAKEFLAGS} .
    cd ${S}/src/fuel-stop-advisor && cmake ${EXTRA_CMAKEFLAGS} .
    cd ${S}/src/hmi/qml/hmi-launcher && cmake ${EXTRA_CMAKEFLAGS} -DQT_MOC=${STAGING_BINDIR_NATIVE}/qt5/moc .
}

do_compile() {
    cd ${S}/src/genivilogreplayer && make
    cd ${S}/src/fuel-stop-advisor && make
    cd ${S}/src/hmi/qml/hmi-launcher && make
}

do_install() {
    install -d ${D}${bindir}/navigation-application/qml
    install -m 0755 ${S}/src/fuel-stop-advisor/fuel-stop-advisor ${D}${bindir}
    install -m 0755 ${S}/src/hmi/qml/hmi-launcher/hmi-launcher ${D}${bindir}
    install -m 0755 ${S}/src/hmi/qml/*.qml ${S}/src/hmi/qml/FSA.qmlproject ${D}/${bindir}/navigation-application/qml
    cp -r ${S}/src/hmi/qml/Core ${D}/${bindir}/navigation-application/qml

    install -d ${D}${libdir}/navigation
    install -m 0755 ${S}/src/genivilogreplayer/genivilogreplayerplugin.so ${D}${libdir}/navigation

    install -d ${D}${sysconfdir}/ambd/
    install -m 0755 ${S}//src/genivilogreplayer/logreplayerconfig.in.json ${D}${sysconfdir}/ambd/
    sed -i 's/..\/..\/..\/build\/genivilogreplayer\//\/usr\/lib\/navigation\//' ${D}${sysconfdir}/ambd/logreplayerconfig.in.json
    sed -i 's/plugins\/dbus\//\/usr\/lib\/automotive-message-broker\//' ${D}${sysconfdir}/ambd/logreplayerconfig.in.json

    tar -xzf ${S}/src/hmi/qml/Core/referenceHMI.tar.gz -C ${D}${bindir}/navigation-application --strip 1

    cp ${WORKDIR}/constants.js ${D}${bindir}/navigation-application/qml/Core
    sed -i 's/..\/..\/..\/build\/hmi\/qml\/hmi-launcher\///' ${D}${bindir}/navigation-application/qml/Core/genivi-origin.js
    cd ${D}${bindir}/navigation-application/qml/Core/
    ln -s genivi-origin.js genivi.js

    install -d ${D}/${libdir}/systemd/user/
    install -m 0644 ${S}/src/fuel-stop-advisor/fuel_stop_advisor.service ${D}/${libdir}/systemd/user

     install -d ${D}/${libdir}/systemd/user/
     install -m 0444 ${S}/src/hmi/qml/hmi-launcher/hmi-launcher_fsa.service ${D}/${libdir}/systemd/user/hmi-launcher_fsa.service
}

FILES_${PN} += "${libdir}/navigation/genivilogreplayerplugin.so"
FILES_${PN} += "${libdir}/systemd/user/*.service \
                /home/root/.config/systemd/user/default.target.wants/*.service"
FILES_${PN}-dbg += "${libdir}/navigation/.debug/"
FILES_${PN} += "${datadir}/navigation-service"

INSANE_SKIP_${PN} = "dev-so"
