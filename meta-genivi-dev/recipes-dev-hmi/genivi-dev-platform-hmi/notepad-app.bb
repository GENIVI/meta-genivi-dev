# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/GENIVI/notepad-app.git;protocol=https \
          "

# Modify these as desired
PV = "1.0+git${SRCPV}"
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
