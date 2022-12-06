########################################################################
#
# Automatically apply code formatting to all modules
#
# Example: ./scripts/format.sh
#
########################################################################

set -xe

./gradlew --init-script scripts/init.gradle.kts spotlessApply