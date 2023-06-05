package br.com.aqueteron.jds.core;

import org.springframework.javapoet.*;
import org.springframework.stereotype.Component;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@SupportedAnnotationTypes({"br.com.aqueteron.jds.core.SimpleDomain"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class DomainServiceProcessor extends AbstractProcessor {

    private static final Logger LOGGER = Logger.getLogger(DomainServiceProcessor.class.getName());

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        LOGGER.finest("Executing DomainServiceProcessor.");
        LOGGER.log(Level.FINEST, "Annotations: {0}", annotations);
        TypeElement simpleDomainTypeElement = this.processingEnv.getElementUtils().getTypeElement(SimpleDomain.class.getCanonicalName());
        if (annotations.contains(simpleDomainTypeElement)) {
            try (Writer writer = this.processingEnv.getFiler().createSourceFile("br.com.aqueteron.jds.core.DefaultDomainServiceMapImpl").openWriter()) {
                JavaFile.builder("br.com.aqueteron.jds.core", TypeSpec
                                .classBuilder("DefaultDomainServiceMapImpl")
                                .addModifiers(Modifier.PUBLIC)
                                .addAnnotation(Component.class)
                                .addSuperinterface(DomainServiceMapProvider.class)
                                .addMethod(createLoadDomainServiceMapMethodSpec(roundEnv.getElementsAnnotatedWith(SimpleDomain.class)))
                                .build())
                        .build()
                        .writeTo(writer);
            } catch (IOException e) {
                LOGGER.severe("Error to write DefaultDomainServiceMapImpl");
                throw new JdsRuntimeException("Error to write DefaultDomainServiceMapImpl", e);
            }
        }
        return true;
    }

    private MethodSpec createLoadDomainServiceMapMethodSpec(final Set<? extends Element> elementsAnnotatedWithSimpleDomain) {
        ClassName arrayList = ClassName.get(ArrayList.class);
        TypeName domainServiceMapArrayList = ParameterizedTypeName.get(
                ClassName.get(List.class),
                ClassName.get(DomainServiceMap.class)
        );
        MethodSpec.Builder loadDomainServiceMapMethodBuilder = MethodSpec
                .methodBuilder("loadDomainServiceMap")
                .addModifiers(Modifier.PUBLIC)
                .returns(List.class)
                .addStatement("$T domainServiceMapArrayList = new $T<>()", domainServiceMapArrayList, arrayList);
        for (Element element : elementsAnnotatedWithSimpleDomain) {
            LOGGER.log(Level.INFO, "Processando a classe: {0}", element.asType());
            LOGGER.log(Level.FINEST, "EnclosingElement: {0}", element.getEnclosingElement());
            LOGGER.log(Level.FINEST, "SimpleName: {0}", element.getSimpleName());
            ClassName enumClassName = ClassName.get(
                    element.getEnclosingElement().toString(),
                    element.getSimpleName().toString()
            );
            SimpleDomain elementSimpleDomain = element.getAnnotation(SimpleDomain.class);
            LOGGER.log(Level.FINE, "Path: {0}", elementSimpleDomain.pathId());
            LOGGER.log(Level.FINE, "IdMethod: {0}", elementSimpleDomain.idMethod());
            LOGGER.log(Level.FINE, "KeyMethod: {0}", elementSimpleDomain.keyMethod());
            loadDomainServiceMapMethodBuilder.addStatement(
                    "domainServiceMapArrayList.add( new DomainServiceMap<>($1T.class, $2S, $1T::$3L, $1T::$4L) )",
                    enumClassName,
                    this.loadServicePath(elementSimpleDomain, enumClassName),
                    this.loadGetIdFunction(elementSimpleDomain),
                    this.loadGetKeyFunction(elementSimpleDomain)
            );
        }
        loadDomainServiceMapMethodBuilder.addStatement("return domainServiceMapArrayList");
        return loadDomainServiceMapMethodBuilder.build();
    }

    private String loadServicePath(final SimpleDomain simpleDomain, final ClassName enumClassName) {
        if (simpleDomain.pathId().isEmpty()) {
            return this.loadDefaultServicePath(enumClassName);
        }
        return simpleDomain.pathId();
    }

    private String loadDefaultServicePath(final ClassName enumClassName) {
        StringBuilder pathStringBuilder = new StringBuilder();
        pathStringBuilder.append(
                enumClassName
                        .simpleName()
                        .replaceAll("([A-Z][a-z])", "-$1")
                        .substring(1)
                        .toLowerCase()
        );
        LOGGER.log(Level.INFO, "Path: {0}", pathStringBuilder);
        return pathStringBuilder.toString();
    }

    private String loadGetIdFunction(final SimpleDomain elementSimpleDomain) {
        return elementSimpleDomain.idMethod();
    }

    private String loadGetKeyFunction(final SimpleDomain elementSimpleDomain) {
        return elementSimpleDomain.keyMethod();
    }

}
