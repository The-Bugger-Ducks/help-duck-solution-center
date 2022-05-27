package com.helpduck.helpducksolutioncenter.model.solution;

import com.helpduck.helpducksolutioncenter.controller.SolutionController;
import com.helpduck.helpducksolutioncenter.model.LinkAdder;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionHateoas;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class SolutionLinkAdder implements LinkAdder<SolutionHateoas> {

	@Override
	public void addLink(Page<SolutionHateoas> solutions) {
		for (SolutionHateoas solution : solutions) {
			String id = solution.getId();
			Link linkToItself = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(SolutionController.class)
							.getSolution(id))
					.withSelfRel();
			solution.add(linkToItself);
		}
	}

	@Override
	public void addLink(SolutionHateoas solution) {
		Link linkToAllsolutions = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(SolutionController.class)
						.getSolutions(null))
				.withRel("solutions");
		solution.add(linkToAllsolutions);
	}
}