package it.unibz.krdb.obda.reformulation.tests;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.Assertion;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.AtomicConceptDescription;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.DLLiterOntology;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.RoleDescription;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.imp.AtomicConceptDescriptionImpl;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.imp.DLLiterConceptInclusionImpl;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.imp.DLLiterRoleInclusionImpl;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.imp.ExistentialConceptDescriptionImpl;
import it.unibz.krdb.obda.owlrefplatform.core.translator.OWLAPI2Translator;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDataFactory;
import org.semanticweb.owl.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owl.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;


public class TranslatorTests extends TestCase {

	public void test_1() throws Exception{
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory(); 
		
		OWLClass class1 = factory.getOWLClass(URI.create("A"));
		OWLObjectProperty prop =  factory.getOWLObjectProperty(URI.create("prop1"));
		
		OWLObjectPropertyRangeAxiom ax = factory.getOWLObjectPropertyRangeAxiom(prop, class1);
		
		OWLOntology onto = manager.createOntology(URI.create("testonto"));
		manager.addAxiom(onto, ax);
		
		OWLAPI2Translator translator = new OWLAPI2Translator();
		DLLiterOntology dlliteonto = translator.translate(onto);
		
		Set<Assertion> ass = dlliteonto.getAssertions();
		Iterator<Assertion> assit = ass.iterator();
		
		assertEquals(1, ass.size());
		DLLiterConceptInclusionImpl a = (DLLiterConceptInclusionImpl) assit.next();
		ExistentialConceptDescriptionImpl ex = (ExistentialConceptDescriptionImpl) a.getIncluded();
		assertEquals(true, ex.isInverse());
		AtomicConceptDescriptionImpl con = (AtomicConceptDescriptionImpl) a.getIncluding();
//		assertEquals(false, con.isInverse());
		
	}
	
	public void test_2() throws Exception{
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory(); 
		
		OWLClass class1 = factory.getOWLClass(URI.create("A"));
		OWLObjectProperty prop =  factory.getOWLObjectProperty(URI.create("prop1"));
		
		OWLObjectPropertyDomainAxiom ax = factory.getOWLObjectPropertyDomainAxiom(prop, class1);
		
		OWLOntology onto = manager.createOntology(URI.create("testonto"));
		manager.addAxiom(onto, ax);
		
		OWLAPI2Translator translator = new OWLAPI2Translator();
		DLLiterOntology dlliteonto = translator.translate(onto);
		
		Set<Assertion> ass = dlliteonto.getAssertions();
		Iterator<Assertion> assit = ass.iterator();

		
		assertEquals(1, ass.size());
		DLLiterConceptInclusionImpl a = (DLLiterConceptInclusionImpl) assit.next();
		ExistentialConceptDescriptionImpl ex = (ExistentialConceptDescriptionImpl) a.getIncluded();
		assertEquals(false, ex.isInverse());
		AtomicConceptDescriptionImpl con = (AtomicConceptDescriptionImpl) a.getIncluding();
//		assertEquals(false, con.isInverse());
		
	}
	
	public void test_3() throws Exception{
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory(); 
		
		OWLObjectProperty prop =  factory.getOWLObjectProperty(URI.create("R"));
		OWLObjectProperty invofprop =  factory.getOWLObjectProperty(URI.create("S"));
		
		OWLInverseObjectPropertiesAxiom ax = factory.getOWLInverseObjectPropertiesAxiom(prop, invofprop);
		
		OWLOntology onto = manager.createOntology(URI.create("testonto"));
		manager.addAxiom(onto, ax);
		
		OWLAPI2Translator translator = new OWLAPI2Translator();
		DLLiterOntology dlliteonto = translator.translate(onto);
		
		Set<Assertion> ass = dlliteonto.getAssertions();
		Iterator<Assertion> assit = ass.iterator();

		
		assertEquals(2, ass.size());
		DLLiterRoleInclusionImpl a = (DLLiterRoleInclusionImpl) assit.next();
		DLLiterRoleInclusionImpl b = (DLLiterRoleInclusionImpl) assit.next();
		
		
		RoleDescription included = (RoleDescription) a.getIncluded();
		assertEquals(false, included.isInverse());
		assertEquals("R", included.getPredicate().getName().toString());
		RoleDescription indlucing = (RoleDescription) a.getIncluding();
		assertEquals(true, indlucing.isInverse());
		assertEquals("S", indlucing.getPredicate().getName().toString());
		
		included = (RoleDescription) b.getIncluded();
		assertEquals(false, included.isInverse());
		assertEquals("S", included.getPredicate().getName().toString());
		indlucing = (RoleDescription) b.getIncluding();
		assertEquals(true, indlucing.isInverse());
		assertEquals("R", indlucing.getPredicate().getName().toString());
		
	}
	
	public void test_4() throws Exception{
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLDataFactory factory = manager.getOWLDataFactory(); 
		
		OWLClass clsA = factory.getOWLClass(URI.create("A"));
		OWLClass clsB = factory.getOWLClass(URI.create("B"));
		
		OWLEquivalentClassesAxiom ax = factory.getOWLEquivalentClassesAxiom(clsA, clsB);
				
		OWLOntology onto = manager.createOntology(URI.create("testonto"));
		manager.addAxiom(onto, ax);
		
		OWLAPI2Translator translator = new OWLAPI2Translator();
		DLLiterOntology dlliteonto = translator.translate(onto);
		
		Set<Assertion> ass = dlliteonto.getAssertions();
		Iterator<Assertion> assit = ass.iterator();

		
		assertEquals(2, ass.size());
		DLLiterConceptInclusionImpl c1 = (DLLiterConceptInclusionImpl) assit.next();
		DLLiterConceptInclusionImpl c2 = (DLLiterConceptInclusionImpl) assit.next();
		
		AtomicConceptDescription included = (AtomicConceptDescription) c1.getIncluded();
		assertEquals("A", included.getPredicate().getName().toString());
		AtomicConceptDescription indlucing = (AtomicConceptDescription) c1.getIncluding();
		assertEquals("B", indlucing.getPredicate().getName().toString());
		
		included = (AtomicConceptDescription) c2.getIncluded();
		assertEquals("B", included.getPredicate().getName().toString());
		indlucing = (AtomicConceptDescription) c2.getIncluding();
		assertEquals("A", indlucing.getPredicate().getName().toString());
		
	}
	
}
