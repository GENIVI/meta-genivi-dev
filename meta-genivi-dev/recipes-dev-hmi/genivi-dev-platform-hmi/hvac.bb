# Copyright (C) 2015-2016 GENIVI Alliance
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE  = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"
SRC_URI = "git://github.com/GENIVI/HVAC"
SRCREV  = "b5f215a90d7c32e75de6a5cc621f5bbc9e5617a0"

SUMMARY = "HVAC"
DEPENDS = "qtbase qtdeclarative dbus"
RDEPENDS_${PN} = "qtquickcontrols-qmlplugins"

S = "${WORKDIR}/git"

inherit qmake5

do_install_append() {
	install -d ${D}/opt/com.genivi.gdp.hvac/bin/imports/
        cp -fr ${S}/imports/* ${D}/opt/com.genivi.gdp.hvac/bin/imports/
        install -d ${D}/opt/com.genivi.gdp.hvac/bin/
	mv ${D}/opt/HVAC_rvi_vtc1010/bin/HVAC_rvi_vtc1010 \ 
		${D}/opt/com.genivi.gdp.hvac/bin/HVAC_rvi_vtc1010
        install -d ${D}/opt/com.genivi.gdp.hvac/share/icons/
        install -m 0444 ${S}/com.genivi.gdp.hvac.svg \
                        ${D}/opt/com.genivi.gdp.hvac/share/icons/com.genivi.gdp.hvac.svg
	install -d ${D}/usr/share/applications/
        install -m 0444 ${S}/com.genivi.gdp.hvac.desktop \
                        ${D}/usr/share/applications/com.genivi.gdp.hvac.desktop
	install -d ${D}${libdir}/systemd/user
}

FILES_${PN} += "\
    /opt/* \
    ${libdir} \
    ${libdir}/systemd \
    ${libdir}/systemd/user \
    ${libdir}/systemd/user/* \
    /usr/share/* \
    "
