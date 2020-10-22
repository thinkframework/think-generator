package io.github.thinkframework.generator.shell;

import org.jline.reader.Parser;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.FileInputProvider;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.stream.Collectors;

/**
 * 自定义的命令行
 */
//@Component
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 100)
public class GeneratorShellApplicationRunner implements ApplicationRunner {

    private final Parser parser;

    private final Shell shell;

    private final ConfigurableEnvironment environment;

    public GeneratorShellApplicationRunner(Parser parser, Shell shell, ConfigurableEnvironment environment) {
        this.parser = parser;
        this.shell = shell;
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InteractiveShellApplicationRunner.disable(environment);
        if (!args.getNonOptionArgs().isEmpty()) {
            String scriptsToRun = args.getNonOptionArgs().stream().collect(Collectors.joining(" "));
            shell.run(new FileInputProvider(new StringReader(scriptsToRun), parser));
        }
    }

}
