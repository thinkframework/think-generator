package org.think.generator;

import org.think.generator.provider.*;
import org.think.generator.util.FreeMarkerUtils;

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
