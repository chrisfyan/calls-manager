package ar.com.cbarone.callsmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({QueueManagerTest.class, CallsManager10Calls.class, CallsManager30Calls.class,
		CallsManager50CallsLessOperators.class })
public class AllTests {

}
