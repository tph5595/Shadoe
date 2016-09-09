import org.watij.webspec.dsl.WebSpec;


public class googleTest {

	public static void main(String[] args) {
		WebSpec spec = new WebSpec().mozilla();
		spec.open("http://www.google.com");
		spec.find.input().with.name("q").set.value("Watij");
		spec.find.input().with.type("button").with.value("Google Search").click();
		//check results
		spec.find.a().with.href("http://watij.com/").shouldExist();

	}

}
