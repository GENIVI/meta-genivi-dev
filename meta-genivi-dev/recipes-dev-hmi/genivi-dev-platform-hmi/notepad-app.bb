LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
SRC_URI = "git://github.com/GENIVI/notepad-app.git;protocol=https \
SRCREV = "75c9e0a86502b99d21c843ebd10e3a06a1093e15"

S = "${WORKDIR}/git"

DEPENDS = " qtdeclarative "
RDEPENDS_${PN} = " persistence-client-library "

inherit qmake5

do_install_append() {
    install -d ${D}/usr/share/applications
    install -m 0444 ${WORKDIR}/notepad-app.desktop \
                    ${D}/usr/share/applications/com.genivi.gdp.notepad.desktop
}

FILES_${PN} += " \
    /usr/share/* \
    /opt/* \
"
