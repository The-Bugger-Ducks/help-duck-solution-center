package com.helpduck.helpducksolutioncenter.model.problem;

import com.helpduck.helpducksolutioncenter.entity.Problem;
// import com.helpduck.helpducksolutioncenter.model.NullStringVerifier;

public class ProblemUpdater {
	// private NullStringVerifier verifier = new NullStringVerifier();

	public void update(Problem problem, Problem updatedProblem) {
		problem.setSolutionList((updatedProblem.getSolutionList()));
	}
}
