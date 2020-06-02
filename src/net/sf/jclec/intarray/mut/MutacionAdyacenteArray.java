package net.sf.jclec.intarray.mut;

import main.ASLB;
import net.sf.jclec.intarray.IntArrayIndividual;
import net.sf.jclec.intarray.IntArrayMutator;

public class MutacionAdyacenteArray extends IntArrayMutator{
	/**
	 * Mutacion adyacente en arrays enteros
	 * 
	 * @author Paula Robles Mateos
	 * @author Maria Merino Pereda
	 * @author Gonzalo Martï¿½nez Salmerï¿½n
	 * 
	 */
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7452255095685882795L;
	
	/**
	 * Empty constructor
	 */
	
	public MutacionAdyacenteArray() {
		super();
	}

	@Override
	protected void mutateNext() {
		// Genome length
				int gl = species.getGenotypeLength();
				// Individual to mutate
				IntArrayIndividual mutant = 
					(IntArrayIndividual) parentsBuffer.get(parentsCounter);
				// Creates mutant genotype
				int [] mgenome = new int[gl];
				System.arraycopy(mutant.getGenotype(), 0, mgenome, 0, gl);
				// Choose mutation point
				int mp = getMutableLocus();
				// Flip selected point
				//Aqui escogemos una posicion aleatoria del genotype 
				//Le añadimos o restamos uno para cambiarlo a una estacion adyacente
				//System.out.println("ESTAMOS EN MUTACION:");
					//System.out.println("EL VALOR MAX ES:"+ASLB.m);
				//si pertenece a la primera estacion le sumamos uno siempre
				if(mgenome[mp]==1) {
				mgenome[mp] ++;
				}
				//Si pertenece a la ultima le restamos uno siempre
				if(mgenome[mp]== ASLB.m) {
					
					mgenome[mp] --;
				}
				else {
					//Si no es ni la primera ni la ultima sumamos o restamos uno aleatoriamente
					int numero = (int) (Math.random() * 2);
						if(numero == 0) {
							mgenome[mp] --;
						}
						else if(numero == 1) {
							mgenome[mp] ++;
						}
						//System.out.println("EL VALOR DEL NUMERO ALEATORIO ES:"+numero);
				}
				flip(mgenome, mp);
				// Returns mutant
				sonsBuffer.add(species.createIndividual(mgenome));
	}

}
