package com.helpduck.helpducksolutioncenter.model.solutionComment;

import com.helpduck.helpducksolutioncenter.entity.SolutionComment;
// import com.helpduck.helpducksolutioncenter.model.NullStringVerifier;

public class SolutionUpdater {
	// private NullStringVerifier verifier = new NullStringVerifier();

	public void update(SolutionComment solution, SolutionComment updatedSolution) {
		solution.setSolutionComment((updatedSolution.getSolutionComment()));
	}

	public void incrementUpVote(SolutionComment solution) {
		solution.setUpVote(solution.getUpVote() + 1);
	}

	public void incrementDownVote(SolutionComment solution) {
		solution.setDownVote(solution.getDownVote() + 1);
	}
}
