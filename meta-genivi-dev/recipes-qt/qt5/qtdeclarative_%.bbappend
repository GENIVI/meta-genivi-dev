FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-V4-Free-up-2-address-bits-in-64bit-mode.patch \
    file://0003-Workaround-crashes-in-QtQml-code-related-to-dead-sto.patch \
"
