# Copyright (C) 2017 GENIVI Alliance
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Notepad-App"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"
SRC_URI = "git://github.com/GENIVI/notepad-app.git;protocol=https"
SRCREV = "bd827f8dc969f4e78d1c7366f19f6d64c3513f8b"

S = "${WORKDIR}/git"

DEPENDS = "qtdeclarative persistence-client-library"

inherit qmake5

SRC_URI_append ="\
    file://notepad-app.desktop \
    "

do_install_append() {
    install -d ${D}/opt/com.genivi.gdp.notepad
    install -m 0444 ${S}/notepad.svg \
                    ${D}/opt/com.genivi.gdp.notepad/notepad.svg

    install -d ${D}${datadir}/applications
    install -m 0444 ${WORKDIR}/notepad-app.desktop \
                    ${D}${datadir}/applications/com.genivi.gdp.notepad.desktop
}

FILES_${PN} += " \
    ${datadir}/* \
    /opt/* \
"
