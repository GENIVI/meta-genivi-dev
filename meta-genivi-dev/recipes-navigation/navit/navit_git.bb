require navit.inc

S = "${WORKDIR}/git"

SRC_URI += "git://github.com/navit-gps/navit.git;branch=trunk;protocol=http \
            file://search_list_get_unique.diff \ 
            file://0001-add-wayland-ilm-support-navit.patch \
            file://0002-add-precision-opengl-ilm-support-navit.patch \
            file://0003-embedded-limited-resources.patch \
            file://fsa_issue_padding.diff \
"

SRCREV = "995cec54c8682fbabfb4f912b6156ce0b5b43436"

