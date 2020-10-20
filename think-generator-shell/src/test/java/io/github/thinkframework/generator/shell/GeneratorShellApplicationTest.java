package io.github.thinkframework.generator.shell;

/**
 * 命令行
 *
 * @author lixiaobin
 */

import org.jline.reader.Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.FileInputProvider;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.StringReader;

/**
* 命令行运行类
*
* @author lixiaobin
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorShellApplication.class)
public class GeneratorShellApplicationTest {

    @Autowired
    private GeneratorShell generatorShell;

    @Autowired
    private Shell shell;

    @Autowired
    private Parser parser;

    @Test
    public void test(){
        generatorShell.datasources();
    }

    @Test
    public void test2() throws IOException {
        shell.run(new FileInputProvider(new StringReader("datasources"), parser));
    }
}
