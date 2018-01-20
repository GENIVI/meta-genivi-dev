SRC_URI = "git://github.com/erlang/otp;branch=master"
SRC_URI += "file://fix-wx-configure.patch" 
SRC_URI += "file://erlang-fix-build-issue-in-Yocto.patch" 

DEPENDS_append_class-native = " ncurses-native openssl-native"

S = "${WORKDIR}/git"
