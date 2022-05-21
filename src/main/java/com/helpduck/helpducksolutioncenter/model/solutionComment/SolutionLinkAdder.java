package com.helpduck.helpducksolutioncenter.model.solutionComment;

import com.helpduck.helpducksolutioncenter.controller.SolutionCommentController;
import com.helpduck.helpducksolutioncenter.model.LinkAdder;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionCommentHateoas;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class SolutionLinkAdder implements LinkAdder<SolutionCommentHateoas> {

	@Override
	public void addLink(Page<SolutionCommentHateoas> solutions) {
		for (SolutionCommentHateoas solution : solutions) {
			String id = solution.getId();
			Link linkToItself = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(SolutionCommentController.class)
							.getSolution(id))
					.withSelfRel();
			solution.add(linkToItself);
		}
	}

	@Override
	public void addLink(SolutionCommentHateoas solution) {
		Link linkToAllsolutions = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(SolutionCommentController.class)
						.getSolutions(null))
				.withRel("solutions");
		solution.add(linkToAllsolutions);
	}
}