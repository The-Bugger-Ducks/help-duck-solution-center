package com.helpduck.helpducksolutioncenter.model.solutionComment;

import com.helpduck.helpducksolutioncenter.entity.SolutionComment;
// import com.helpduck.helpducksolutioncenter.model.NullStringVerifier;

public class SolutionUpdater {
	// private NullStringVerifier verifier = new NullStringVerifier();

	public void update(SolutionComment solution, SolutionComment updatedSolution) {
		solution.setSolutionComment((updatedSolution.getSolutionComment()));
	}
}
