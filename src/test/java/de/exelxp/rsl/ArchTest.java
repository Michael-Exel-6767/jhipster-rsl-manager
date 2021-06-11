package de.exelxp.rsl;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.exelxp.rsl");

        noClasses()
            .that()
            .resideInAnyPackage("de.exelxp.rsl.service..")
            .or()
            .resideInAnyPackage("de.exelxp.rsl.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..de.exelxp.rsl.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
