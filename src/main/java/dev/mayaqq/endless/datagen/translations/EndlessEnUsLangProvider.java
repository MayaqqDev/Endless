package dev.mayaqq.endless.datagen.translations;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class EndlessEnUsLangProvider extends FabricLanguageProvider {
    public EndlessEnUsLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        EndlessTranslations.generateEnUsTranslations(translationBuilder);

    }
}