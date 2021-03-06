package it.unibz.inf.ontop.model.term.impl;

/*
 * #%L
 * ontop-obdalib-core
 * %%
 * Copyright (C) 2009 - 2014 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import it.unibz.inf.ontop.model.term.functionsymbol.URITemplatePredicate;

public class URITemplatePredicateImpl extends PredicateImpl implements URITemplatePredicate {

	// The name of the function that creates URI's in Quest
	private static final String URI_PREFIX = "URI";
	private static final long serialVersionUID = 1L;

	public URITemplatePredicateImpl(int arity) {
		// TODO: BAD CODE! Predicate shouldn't store the arity and the type.
		super(URI_PREFIX  + arity, arity, null);
	}
	
	@Override
	public URITemplatePredicateImpl clone() {
		return this;
	}
}
