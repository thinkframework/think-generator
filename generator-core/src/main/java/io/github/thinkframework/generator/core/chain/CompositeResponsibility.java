package io.github.thinkframework.generator.core.chain;

import io.github.thinkframework.generator.core.context.GeneratorContext;

import java.util.List;

/**
 * 组合模式
 * @author hdhxby
 * @since 2017/3/24
 */
public class CompositeResponsibility extends AbstractResponsibility implements GeneratorResponsibility {

    private List<GeneratorResponsibility> generatorResponsibilitys;

    public List<GeneratorResponsibility> getGeneratorResponsibilitys() {
        return generatorResponsibilitys;
    }

    public void setGeneratorResponsibilitys(List<GeneratorResponsibility> generatorResponsibilitys) {
        this.generatorResponsibilitys = generatorResponsibilitys;
    }

    @Override
    public GeneratorContext apply(GeneratorContext generatorContext) {
        for (GeneratorResponsibility generatorResponsibility : generatorResponsibilitys) {
            generatorResponsibility.apply(generatorContext);
        }
        return generatorContext;
    }
}
