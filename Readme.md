
I have a problem building this project with maven
if run `mvn package`
i get this error

```
[INFO] --- bnd-export-maven-plugin:4.1.0:export (default) @ client-app ---
[ERROR] Error   : The runbundles have changed. Failing the build!
```
if run 
``` 
mvn -pl client-app -am  bnd-indexer:index bnd-indexer:index@test-index bnd-resolver:resolve package
```
it add the impl bundle that provide `ExampleRemoteService` supposed to be provided by remote service on server-app

if add 
```
-runblacklist: \
	osgi.identity;filter:='(osgi.identity=org.osgi.enroute.examples.microservice.impl)'
```
to /client-app/client.bndrun

```
mvn -pl client-app -am  bnd-indexer:index bnd-indexer:index@test-index bnd-resolver:resolve package
```
Fail with
```
[ERROR] Failed to execute goal biz.aQute.bnd:bnd-resolver-maven-plugin:4.1.0:resolve (default-cli) on project client-app: Unable to resolve <<INITIAL>>: missing requirement osgi.identity;filter:='(osgi.identity=org.osgi.enroute.examples.microservice.consumer)' [caused by: Unable to resolve org.osgi.enroute.examples.microservice.consumer version=0.0.1.201903282239: missing requirement osgi.service;filter:='(objectClass=SampleRemoteApi.ExampleRemoteService)';effective:='active'] -> [Help 1]
```

use eclipse bndtools plugin to run client and server application 

* https://github.com/bndtools/bnd/issues/3111
* https://github.com/bndtools/bnd/issues/2980
