package com.helpduck.helpducksolutioncenter.model.problem;

import com.helpduck.helpducksolutioncenter.controller.ProblemController;
import com.helpduck.helpducksolutioncenter.model.LinkAdder;
import com.helpduck.helpducksolutioncenter.model.hateoas.ProblemHateoas;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProblemLinkAdder implements LinkAdder<ProblemHateoas> {

	@Override
	public void addLink(Page<ProblemHateoas> problems) {
		for (ProblemHateoas problem : problems) {
			String id = problem.getId();
			Link linkToItself = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ProblemController.class)
							.getProblem(id))
					.withSelfRel();
			problem.add(linkToItself);
		}
	}

	@Override
	public void addLink(ProblemHateoas problem) {
		Link linkToAllproblems = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ProblemController.class)
						.getProblems(null))
				.withRel("problems");
		problem.add(linkToAllproblems);
	}
}