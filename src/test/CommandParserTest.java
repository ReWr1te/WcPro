import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileReader;
import java.io.BufferedReader;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * Tian Shiyuan, SSE, HUST: This is an test of CommandParserTest class,
 *  which is a JUnit class for testing class CommandParser
 */

public class CommandParserTest {
    private CommandParser cp;

    @Before
    public void start() {
        cp = new CommandParser();
        System.out.println("Unit Test Started.");
    }

    @After
    public void finish() {
        System.out.println("Unit Test Finished.");
    }

    @Test
    public void parseCommandTest0() throws Exception { // #0 zero-parameter test (black-box)
        String[] paras = {};
        System.out.println("parseCommand() Test0 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test0 succeeded.");
        System.out.println("parseCommand() Test0 finished.");
    }

    @Test
    public void parseCommandTest1() throws Exception { // #1  multi-parameter test (black-box)
        String[] paras = {"test0.txt", "test1.txt"};
        System.out.println("parseCommand() Test1 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test1 succeeded.");
        System.out.println("parseCommand() Test1 finished.");
    }

    @Test
    public void parseCommandTest2() throws Exception { // #2 paths test Mac (black-box)
        String[] paras = {"/Mac/PathsTest0.txt"};
        System.out.println("parseCommand() Test2 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test2 succeeded.");
        System.out.println("parseCommand() Test2 finished.");
    }

    @Test
    public void parseCommandTest3() throws Exception { // #3 paths test Windows (black-box)
        String[] paras = {"D:\\Windows\\PathsTest1.txt"};
        System.out.println("parseCommand() Test3 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test3 succeeded.");
        System.out.println("parseCommand() Test3 finished.");
    }

    @Test
    public void parseCommandTest4() throws Exception { // #4 multi-file test (black-box)
        String[] paras = {"*.txt"};
        System.out.println("parseCommand() Test4 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test4 succeeded.");
        System.out.println("parseCommand() Test4 finished.");
    }

    @Test
    public void parseCommandTest5() throws Exception { // #5 non-txt file test (black-box)
        String[] paras = {"txtFileTest.java"};
        System.out.println("parseCommand() Test5 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test5 succeeded.");
        System.out.println("parseCommand() Test5 finished.");
    }

    @Test
    public void parseCommandTest6() throws Exception { // #6 one-line file test (black-box)
        String[] paras = {"test0.txt"};
        System.out.println("parseCommand() Test6 started.");
        assertEquals("Failed","hello world!\n", cp.parseCommand(paras));
        System.out.println("parseCommand() Test6 succeeded.");
        System.out.println("parseCommand() Test6 finished.");
    }

    @Test
    public void parseCommandTest7() throws Exception { // #7 null file test (black-box)
        String[] paras = {"test1.txt"};
        System.out.println("parseCommand() Test7 started.");
        assertEquals("Failed","", cp.parseCommand(paras));
        System.out.println("parseCommand() Test7 succeeded.");
        System.out.println("parseCommand() Test7 finished.");
    }

    @Test
    public void parseCommandTest8() throws Exception { // #8 multi-line file test (black-box)
        String[] paras = {"test2.txt"};
        System.out.println("parseCommand() Test8 started.");
        assertEquals("Failed","hello world!\nhello world~\n", cp.parseCommand(paras));
        System.out.println("parseCommand() Test8 succeeded.");
        System.out.println("parseCommand() Test8 finished.");
    }

    @Test
    public void parseCommandTest9() throws Exception { // #9 full-width char test (black-box)
        String[] paras = {"test5.txt"};
        System.out.println("parseCommand() Test9 started.");
        assertEquals("Failed",null, cp.parseCommand(paras));
        System.out.println("parseCommand() Test9 succeeded.");
        System.out.println("parseCommand() Test9 finished.");
    }

    @Test
    public void paraHandlingZeroParaTest() throws Exception { // #10 zero para test (white-box)
        String[] paras = {};
        System.out.println("paraHandling() Zero Parameter Test started.");
        assertEquals("Failed", 0, cp.paraHandling(paras));
        System.out.println("paraHandling() Zero Parameter Test succeeded.");
        System.out.println("paraHandling() Zero Parameter Test finished.");
    }

    @Test
    public void paraHandlingMultiParasTest() throws Exception { // #11 multi-para test (white-box)
        String[] paras = {"test0.txt", "test1.txt"};
        System.out.println("paraHandling() Multi-Parameter Test started.");
        assertEquals("Failed", 0, cp.paraHandling(paras));
        System.out.println("paraHandling() Multi-Parameter Test succeeded.");
        System.out.println("paraHandling() Multi-Parameter Test finished.");
    }

    @Test
    public void paraHandlingPathsTest0() throws Exception { // #12 paths test Mac (white-box)
        String[] paras = {"/Mac/PathsTest0.txt"};
        System.out.println("paraHandling() Paths Test0 started.");
        assertEquals("Failed", 0, cp.paraHandling(paras));
        System.out.println("paraHandling() Paths Test0 succeeded.");
        System.out.println("paraHandling() Paths Test0 finished.");
    }

    @Test
    public void paraHandlingPathsTest1() throws Exception { // #13 paths test Windows (white-box)
        String[] paras = {"D:\\Windows\\PathsTest1.txt"};
        System.out.println("paraHandling() Paths Test1 started.");
        assertEquals("Failed", 0, cp.paraHandling(paras));
        System.out.println("paraHandling() Paths Test1 succeeded.");
        System.out.println("paraHandling() Paths Test1 finished.");
    }

    @Test
    public void paraHandlingMultiFilesTest() throws Exception { // #14 multi-file test (white-box)
        String[] paras = {"*.txt"};
        System.out.println("paraHandling() Multi-File Test started.");
        assertEquals("Failed", 0, cp.paraHandling(paras));
        System.out.println("paraHandling() Multi-File Test succeeded.");
        System.out.println("paraHandling() Multi-File Test finished.");
    }

    @Test
    public void paraHandlingTxtFileTest() throws Exception { // #15 non-txt file test (white-box)
        String[] paras = {"txtFileTest.java"};
        System.out.println("paraHandling() .txt File Test started.");
        assertEquals("Failed", 0, cp.paraHandling(paras));
        System.out.println("paraHandling() .txt File Test succeeded.");
        System.out.println("paraHandling() .txt File Test finished.");
    }

    @Test
    public void getAddressTest() throws Exception { // #16 get address test (white-box)
        String os = System.getProperty("os.name");
        System.out.println("getAddress() Test started.");
        if (os.indexOf("Mac") != -1)
            assertEquals("Failed", System.getProperty("user.dir") + "/" + "test0.txt",
                    cp.getAddress("test0.txt")); // OSX
        else if (os.indexOf("Win") != -1)
            assertEquals("Failed", System.getProperty("user.dir") + "\\" + "test0.txt",
                    cp.getAddress("test0.txt")); // Windows
        System.out.println("getAddress() Test succeeded.");
        System.out.println("getAddress() Test finished.");
    }

    @Test
    public void getContentTest0() throws Exception { // #17 one-line content test (white-box)
        String address = "/Users/tianshiyuan/Documents/Projects/IdeaProjects/WcPro/test0.txt";
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        System.out.println("getContent() Test0 started.");
        assertEquals("Failed", "hello world!\n", cp.getContent(fr, br, sb, address));
        System.out.println("getContent() Test0 succeeded.");
        System.out.println("getContent() Test0 finished.");
    }

    @Test
    public void getContentTest1() throws Exception { // #18 null content test (white-box)
        String address = "/Users/tianshiyuan/Documents/Projects/IdeaProjects/WcPro/test1.txt";
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        System.out.println("getContent() Test1 started.");
        assertEquals("Failed", "", cp.getContent(fr, br, sb, address));
        System.out.println("getContent() Test1 succeeded.");
        System.out.println("getContent() Test1 finished.");
    }

    @Test
    public void getContentTest2() throws Exception { // #19 multi-line content test (white-box)
        String address = "/Users/tianshiyuan/Documents/Projects/IdeaProjects/WcPro/test2.txt";
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        System.out.println("getContent() Test2 started.");
        assertEquals("Failed", "hello world!\nhello world~\n", cp.getContent(fr, br, sb, address));
        System.out.println("getContent() Test2 succeeded.");
        System.out.println("getContent() Test2 finished.");
    }

    @Test
    public void halfWidthCharTest0() {
        System.out.println("halfWidthChar() Test0 started."); // #20 half-width char test correct (white-box)
        assertEquals("Failed",1, cp.halfWidthChar("hello world!"));
        System.out.println("halfWidthChar() Test0 succeeded.");
        System.out.println("halfWidthChar() Test0 finished.");
    }

    @Test
    public void halfWidthCharTest1() {
        System.out.println("halfWidthChar() Test1 started."); // #21 full-width char test wrong (white-box)
        assertEquals("Failed",0, cp.halfWidthChar("你好！"));
        System.out.println("halfWidthChar() Test1 succeeded.");
        System.out.println("halfWidthChar() Test1 finished.");
    }
}