package matrizindividual;

import net.sf.jclec.IIndividual;
import net.sf.jclec.base.AbstractIndividual;


public class MutacionAdyacente extends MatrizMutator{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8292769214034993853L;

	/**
	 * Empty constructor
	 */

	public MutacionAdyacente()
	{
		super();
	}

	@Override
	protected void mutateNext() {
		// TODO Auto-generated method stub
		// Gets the genotype length
		int gl = species.getGenotypeLength();
		int [][] mgenome = new int[gl][];
		
		
		// Creates the individual to mutate
		IIndividual mutant = parentsBuffer.get(parentsCounter);
		
		int cp1 = randgen.choose(0, gl-1);
		
		
		System.arraycopy(((AbstractIndividual<int[][]>) mutant).getGenotype(),0, mgenome,0, gl);
		
	
		flip2(mgenome,cp1);
		
		// Returns the mutated individual
		sonsBuffer.add(species.createIndividual(mgenome));
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof MutacionAdyacente) {
			return true;
		}
		else {
			return false;
		}
	}
}




