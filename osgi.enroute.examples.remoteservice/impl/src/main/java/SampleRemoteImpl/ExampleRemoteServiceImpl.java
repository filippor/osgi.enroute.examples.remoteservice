package SampleRemoteImpl;

import org.osgi.annotation.bundle.Requirement;
import org.osgi.annotation.bundle.Requirements;
import org.osgi.namespace.extender.ExtenderNamespace;
import org.osgi.service.component.annotations.*;

import SampleRemoteApi.ExampleRemoteService;

@Requirements(value = {
//osgi.extender; osgi.extender=osgi.remoteserviceadmin; version:Version=1.1
		@Requirement(namespace = ExtenderNamespace.EXTENDER_NAMESPACE, name = "osgi.remoteserviceadmin", version = "1.1"),
		// Provide-Capability:
		// osgi.remoteserviceadmin.discovery;protocols:List<String>="ZOOKEEPER,ecf.discovery.zoodiscovery,ecf.discovery.zoodiscovery.locator,ecf.discovery.zoodiscovery.advertiser";version:Version=1.1
		@Requirement(
				namespace = "osgi.remoteserviceadmin.discovery"
				,filter="(protocols=ZEROCONF)"
				),
		@Requirement(
				namespace = "osgi.remoteserviceadmin.topology"
				),
		//Provide-Capability: osgi.remoteserviceadmin.distribution; configs:List<String>="ecf.rest.client"; version:Version=1.1 
		@Requirement(
				namespace = "osgi.remoteserviceadmin.distribution"
				,filter="(&(configs=ecf.generic.server)(configs=ecf.generic.client))"
				),
		

})
@Component(property = { "service.exported.interfaces=*", "service.exported.intents=osgi.basic" })
public class ExampleRemoteServiceImpl implements ExampleRemoteService {

	public String hello(String name) {
		System.out.println("ExampleRemoteServiceImpl.hello called with name=" + name);
		return "Hello " + name;
	}

}
