package io.github.thinkframework.generator;

import io.github.thinkframework.generator.provider.GeneratorProvider;
import io.github.thinkframework.generator.provider.TableGeneratorProvider;
import org.think.generator.provider.*;
import io.github.thinkframework.generator.util.FreeMarkerUtils;

import java.util.*;

public class Generator {

    public Generator() {

    }

    public Generator generate() {
        List<GeneratorProvider> providers = getGeneratorProviders();
        Map map = new HashMap();

        for (GeneratorProvider generatorProvider : providers) {
            map.putAll(generatorProvider.build(map));
        }

        new FreeMarkerUtils().process(map);
        return this;
    }

    private List<GeneratorProvider> getGeneratorProviders() {
        List<GeneratorProvider> providers = new ArrayList<>();
        providers.add(new TableGeneratorProvider());
        return providers;
    }
}
