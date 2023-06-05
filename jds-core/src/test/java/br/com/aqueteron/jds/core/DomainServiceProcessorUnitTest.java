package br.com.aqueteron.jds.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DomainServiceProcessorUnitTest {

    private DomainServiceProcessor domainServiceProcessor;

    @Mock
    private ProcessingEnvironment processingEnvironmentMock;

    @BeforeEach
    void setUp() {
        this.domainServiceProcessor = new DomainServiceProcessor();
        this.domainServiceProcessor.init(processingEnvironmentMock);
    }

    @Test
    void processWithEmptyNotationsTest() {
        Elements elementsMock = mock(Elements.class);
        TypeElement simpleDomainTypeElementMock = mock(TypeElement.class);
        Set<TypeElement> annotations = new HashSet<>();

        when(this.processingEnvironmentMock.getElementUtils()).thenReturn(elementsMock);
        when(elementsMock.getTypeElement("br.com.aqueteron.jds.core.SimpleDomain")).thenReturn(simpleDomainTypeElementMock);
        var result = this.domainServiceProcessor.process(annotations, null);
        assertTrue(result);
    }

    @Test
    void shouldProcessWithIOExceptionOnOpenWriterTest() throws IOException {
        Elements elementsMock = mock(Elements.class);
        TypeElement simpleDomainTypeElementMock = mock(TypeElement.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(simpleDomainTypeElementMock);
        Filer filerMock = mock(Filer.class);
        JavaFileObject javaFileObjectMock = mock(JavaFileObject.class);
        RoundEnvironment roundEnvMock = mock(RoundEnvironment.class);
        IOException ioException = new IOException();

        when(this.processingEnvironmentMock.getElementUtils()).thenReturn(elementsMock);
        when(elementsMock.getTypeElement("br.com.aqueteron.jds.core.SimpleDomain")).thenReturn(simpleDomainTypeElementMock);
        when(this.processingEnvironmentMock.getFiler()).thenReturn(filerMock);
        when(filerMock.createSourceFile("br.com.aqueteron.jds.core.DefaultDomainServiceMapImpl")).thenReturn(javaFileObjectMock);
        when(javaFileObjectMock.openWriter()).thenThrow(ioException);
        try {
            var result = this.domainServiceProcessor.process(annotations, roundEnvMock);
        } catch (JdsRuntimeException jre) {
            assertEquals("Error to write DefaultDomainServiceMapImpl", jre.getMessage());
            assertEquals(ioException, jre.getCause());
        }
    }

    @Test
    void processWithEmptySimpleDomainSetTest() throws IOException {
        Elements elementsMock = mock(Elements.class);
        TypeElement simpleDomainTypeElementMock = mock(TypeElement.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(simpleDomainTypeElementMock);
        Filer filerMock = mock(Filer.class);
        JavaFileObject javaFileObjectMock = mock(JavaFileObject.class);
        Writer writerMock = mock(Writer.class);
        RoundEnvironment roundEnvMock = mock(RoundEnvironment.class);

        when(this.processingEnvironmentMock.getElementUtils()).thenReturn(elementsMock);
        when(elementsMock.getTypeElement("br.com.aqueteron.jds.core.SimpleDomain")).thenReturn(simpleDomainTypeElementMock);
        when(this.processingEnvironmentMock.getFiler()).thenReturn(filerMock);
        when(filerMock.createSourceFile("br.com.aqueteron.jds.core.DefaultDomainServiceMapImpl")).thenReturn(javaFileObjectMock);
        when(javaFileObjectMock.openWriter()).thenReturn(writerMock);
        when(roundEnvMock.getElementsAnnotatedWith(SimpleDomain.class)).thenReturn(new HashSet<>());
        var result = this.domainServiceProcessor.process(annotations, roundEnvMock);
        assertTrue(result);
    }

    @Test
    void processWithEmptyPathTest() throws IOException {
        Elements elementsMock = mock(Elements.class);
        TypeElement simpleDomainTypeElementMock = mock(TypeElement.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(simpleDomainTypeElementMock);
        Filer filerMock = mock(Filer.class);
        JavaFileObject javaFileObjectMock = mock(JavaFileObject.class);
        Writer writerMock = mock(Writer.class);
        RoundEnvironment roundEnvMock = mock(RoundEnvironment.class);
        Set elementSet = new HashSet<>();
        Element elementMock = mock(Element.class);
        elementSet.add(elementMock);
        Element subElementMock = mock(Element.class);
        Name nameMock = mock(Name.class);
        SimpleDomain simpleDomainMock = mock(SimpleDomain.class);

        when(this.processingEnvironmentMock.getElementUtils()).thenReturn(elementsMock);
        when(elementsMock.getTypeElement("br.com.aqueteron.jds.core.SimpleDomain")).thenReturn(simpleDomainTypeElementMock);
        when(this.processingEnvironmentMock.getFiler()).thenReturn(filerMock);
        when(filerMock.createSourceFile("br.com.aqueteron.jds.core.DefaultDomainServiceMapImpl")).thenReturn(javaFileObjectMock);
        when(javaFileObjectMock.openWriter()).thenReturn(writerMock);
        when(roundEnvMock.getElementsAnnotatedWith(SimpleDomain.class)).thenReturn(elementSet);
        when(elementMock.getEnclosingElement()).thenReturn(subElementMock);
        when(subElementMock.toString()).thenReturn("subElement.toString");
        when(elementMock.getSimpleName()).thenReturn(nameMock);
        when(nameMock.toString().toString()).thenReturn("name.toString");
        when(elementMock.getAnnotation(SimpleDomain.class)).thenReturn(simpleDomainMock);
        when(simpleDomainMock.pathId()).thenReturn("");
        var result = this.domainServiceProcessor.process(annotations, roundEnvMock);
        assertTrue(result);
    }

    @Test
    void shouldProcessWithAllSetTest() throws IOException {
        Elements elementsMock = mock(Elements.class);
        TypeElement simpleDomainTypeElementMock = mock(TypeElement.class);
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(simpleDomainTypeElementMock);
        Filer filerMock = mock(Filer.class);
        JavaFileObject javaFileObjectMock = mock(JavaFileObject.class);
        Writer writerMock = mock(Writer.class);
        RoundEnvironment roundEnvMock = mock(RoundEnvironment.class);
        Set elementSet = new HashSet<>();
        Element elementMock = mock(Element.class);
        elementSet.add(elementMock);
        Element subElementMock = mock(Element.class);
        Name nameMock = mock(Name.class);
        SimpleDomain simpleDomainMock = mock(SimpleDomain.class);

        when(this.processingEnvironmentMock.getElementUtils()).thenReturn(elementsMock);
        when(elementsMock.getTypeElement("br.com.aqueteron.jds.core.SimpleDomain")).thenReturn(simpleDomainTypeElementMock);
        when(this.processingEnvironmentMock.getFiler()).thenReturn(filerMock);
        when(filerMock.createSourceFile("br.com.aqueteron.jds.core.DefaultDomainServiceMapImpl")).thenReturn(javaFileObjectMock);
        when(javaFileObjectMock.openWriter()).thenReturn(writerMock);
        when(roundEnvMock.getElementsAnnotatedWith(SimpleDomain.class)).thenReturn(elementSet);
        when(elementMock.getEnclosingElement()).thenReturn(subElementMock);
        when(subElementMock.toString()).thenReturn("subElement.toString");
        when(elementMock.getSimpleName()).thenReturn(nameMock);
        when(nameMock.toString().toString()).thenReturn("name.toString");
        when(elementMock.getAnnotation(SimpleDomain.class)).thenReturn(simpleDomainMock);
        when(simpleDomainMock.pathId()).thenReturn("pathId");
        var result = this.domainServiceProcessor.process(annotations, roundEnvMock);
        assertTrue(result);
    }

}
