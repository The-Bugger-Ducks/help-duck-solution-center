package com.helpduck.helpducksolutioncenter.model.problem;

import java.util.List;

import com.helpduck.helpducksolutioncenter.entity.Problem;
// import com.helpduck.helpducksolutioncenter.model.NullStringVerifier;
import com.helpduck.helpducksolutioncenter.entity.Solution;

public class ProblemUpdater {
	// private NullStringVerifier verifier = new NullStringVerifier();

	public void incrementSolutionList(Problem problem, Solution newSolution) {
		List<Solution> listSolution = problem.getSolutionList();
		listSolution.add(newSolution);

		problem.setSolutionList(listSolution);
	}
}
