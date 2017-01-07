FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_append_koelsch = " libegl"

PACKAGECONFIG_GL = "gles2"
PACKAGECONFIG_append = " icu accessibility eglfs"
PACKAGECONFIG_append_rpi = " fontconfig"

QT_CONFIG_FLAGS_append = " -qpa wayland"
