package test.net.sourceforge.pmd.rules;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.rules.XPathRule;

public class UnnecessaryReturnTest extends SimpleAggregatorTst{

    private Rule rule;

    public void setUp() {
        rule = new XPathRule();
        rule.addProperty("xpath", "//ResultType[@Void='true' and parent::MethodDeclaration[Block/BlockStatement/Statement/ReturnStatement]]");
    }

    public void testAll() {
       runTests(new TestDescriptor[] {
           new TestDescriptor(TEST1, "bad", 1, rule),
           new TestDescriptor(TEST2, "ok since method is not void", 0, rule),
           new TestDescriptor(TEST3, "ok since return is in sub block", 0, rule),
           new TestDescriptor(TEST4, "interface methods work ok", 0, rule),
           new TestDescriptor(TEST5, "abstract methods work ok", 0, rule),
       });
    }

    private static final String TEST1 =
    "public class Foo {" + PMD.EOL +
    " void bar() {" + PMD.EOL +
    "  int y = 3;" + PMD.EOL +
    "  return;" + PMD.EOL +
    " }" + PMD.EOL +
    "}";

    private static final String TEST2 =
    "public class Foo {" + PMD.EOL +
    " int bar() {" + PMD.EOL +
    "  return 2;" + PMD.EOL +
    " }" + PMD.EOL +
    "}";

    private static final String TEST3 =
    "public class Foo {" + PMD.EOL +
    " void bar() {" + PMD.EOL +
    "  if (false) return;" + PMD.EOL +
    " }" + PMD.EOL +
    "}";

    private static final String TEST4 =
    "public interface Foo {" + PMD.EOL +
    " void bar();" + PMD.EOL +
    "}";

    private static final String TEST5 =
    "public class Foo {" + PMD.EOL +
    " abstract void bar();" + PMD.EOL +
    "}";

}
