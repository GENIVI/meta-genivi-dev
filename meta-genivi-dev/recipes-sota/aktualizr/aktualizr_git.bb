SUMMARY = "Aktualizr SOTA Client"
DESCRIPTION = "SOTA Client written in C++"
HOMEPAGE = "https://github.com/advancedtelematic/aktualizr"
SECTION = "base"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9741c346eef56131163e13b9db1241b3"

inherit cmake systemd

S = "${WORKDIR}/git"

SRCREV = "a6136f091d58b1bb97ec0dd1215eb570fa853f30"

SRC_URI = " \
          git://github.com/advancedtelematic/aktualizr;branch=fix/boost-yocto \
          file://aktualizr.service \
          "

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "aktualizr.service"

DEPENDS = "boost curl openssl jansson dbus"
RDEPENDS_aktualizr = "dbus-lib"

EXTRA_OECMAKE = "-DWARNING_AS_ERROR=OFF -DCMAKE_BUILD_TYPE=Release -DBUILD_TESTS=OFF -DBUILD_GENIVI=ON"

do_install() {
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/aktualizr.service ${D}${systemd_unitdir}/system
}

FILES_${PN} = " \
              ${bindir}/aktualizr \
              ${libdir}/libjwt.so.0 \
              ${libdir}/libjwt.so.0.3.0 \
              ${systemd_unitdir}/system/aktualizr.service \
              "
