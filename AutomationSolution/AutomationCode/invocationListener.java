//package AutomationCode;
//import java.util.Collection;
//
//import org.testng.IInvokedMethod;
//import org.testng.IInvokedMethodListener;
//import org.testng.ISuite;
//import org.testng.ITestResult;
//import org.testng.xml.XmlTest;
//import junit.awtui.TestRunner;
//
//
//public class invocationListener implements IInvokedMethodListener
//{
//
//
////	private String customToken;
//
////	@Override
////	public String getCustomToken(Person person)
////	{
////	    FirebaseAuth.getInstance().createCustomToken(person.getUid()).addOnSuccessListener(new OnSuccessListener<String>() {
////	        @Override
////	        public void onSuccess(String customToken) {
////	            this.customToken = customToken
////	        }
////	    });
////	    return null;
////	}
//
//
//	@Override
//	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1)
//	{
//
//		System.out.println("Invocation Count "+arg0.getTestMethod().getCurrentInvocationCount());
//		System.out.println(arg0.getTestResult().getMethod().getInvocationCount());
//		System.out.println("MIH");
//	}
//
//
//
//	@Override
//	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1)
//	{
//		// TODO Auto-generated method stub
//
//	}
//}