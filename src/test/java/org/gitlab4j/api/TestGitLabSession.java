package org.gitlab4j.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * In order for these tests to run you must set the following systems properties:
 * 
 * TEST_HOST_URL
 * TEST_USERNAME
 * TEST_PASSWORD
 * 
 * If any of the above are NULL, all tests in this class will be skipped. If running from mvn simply
 * use a command line similar to:
 * 
 * mvn test -DTEST_HOST_URL=https://gitlab.com -DTTEST_USERNAME=your_username -DTEST_PASSWORD=your_strong_password
 */
public class TestGitLabSession {

    // The following needs to be set to your test repository
    private static final String TEST_USERNAME;
    private static final String TEST_PASSWORD;
    private static final String TEST_HOST_URL;
    static {
        TEST_USERNAME = System.getProperty("TEST_USERNAME");
        TEST_PASSWORD = System.getProperty("TEST_PASSWORD");
        TEST_HOST_URL = System.getProperty("TEST_HOST_URL");
    }

    private static String problems = "";

    public TestGitLabSession() {
        super();
    }

    @BeforeClass
    public static void setup() {

        problems = "";

        if (TEST_USERNAME == null || TEST_USERNAME.trim().length() == 0) {
            problems += "TEST_USERNAME cannot be empty\n";
        }

        if (TEST_PASSWORD == null || TEST_PASSWORD.trim().length() == 0) {
            problems += "TEST_PASSWORD cannot be empty\n";
        }

        if (TEST_HOST_URL == null || TEST_HOST_URL.trim().length() == 0) {
            problems += "TEST_HOST_URL cannot be empty\n";
        }

        if (!problems.isEmpty()) {
            System.err.print(problems);
        }
    }

    @Before
    public void beforeMethod() {
        assumeTrue(problems != null && problems.isEmpty());
    }

    @Test
    public void testSession() throws GitLabApiException {
        GitLabApi gitlabApi = GitLabApi.create(TEST_HOST_URL, TEST_USERNAME, TEST_PASSWORD);
        assertNotNull(gitlabApi);
    }
}
