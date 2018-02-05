# activate py36
import subprocess
import os

debugPort = 7777
debugKey = "SPARK_SUBMIT_OPTS"
debugparam = "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=" + str(debugPort)
#os.putenv(debugKey, debugparam)
subprocess.run(["sbt.bat", "package"])
