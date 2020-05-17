#!/usr/bin/env bash
cp /opt/nexus-3/bin/nexus.vmoptions.tmp /opt/nexus-3/bin/nexus.vmoptions
echo -Xmx$JVM_MAXIMUM_MEMORY >> /opt/nexus-3/bin/nexus.vmoptions
echo -Xms$JVM_MINIMUM_MEMORY >> /opt/nexus-3/bin/nexus.vmoptions
echo -XX:MaxDirectMemorySize=$JVM_MAXDIRECTMEMORYSIZE >> /opt/nexus-3/bin/nexus.vmoptions
/opt/nexus-3/bin/nexus run