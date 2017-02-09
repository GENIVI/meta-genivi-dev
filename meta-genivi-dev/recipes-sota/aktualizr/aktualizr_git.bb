SUMMARY = "Aktualizr SOTA Client"
DESCRIPTION = "SOTA Client written in C++"
HOMEPAGE = "https://github.com/advancedtelematic/aktualizr"
SECTION = "base"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9741c346eef56131163e13b9db1241b3"

inherit cmake systemd

S = "${WORKDIR}/git"

SRCREV = "1b7b9d92f432188f7c12ab19d4f736098b199a9f"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
          git://github.com/advancedtelematic/aktualizr \
          "

DEPENDS = "boost curl openssl dbus common-api-c++-dbus dlt-daemon gmock gtest"
RDEPENDS = ""

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DBUILD_WITH_DBUS_GATEWAY=ON -DSTAGING_DIR_TARGET=${STAGING_DIR_TARGET} -DGMOCK_ROOT=${STAGING_DIR_TARGET}${includedir}/gmock"

do_install() {
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/build/target/sota_client ${D}${bindir}/aktualizr
}

FILES_${PN} = " \
              ${bindir}/aktualizr \
              "
