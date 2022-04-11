package sort;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {
	public static void countingSort(int[] arranjo, int maior) {
		// inicializa veto auxilia com tamanho igual ao maior do arranjo
		int[] vetorAuxiliar = new int[maior + 1];

		// conta as ocorrências dos números e salva no vetor auxiliar
		for (int i = 0; i < arranjo.length; i++) {
			vetorAuxiliar[arranjo[i]]++;
		}

		// marca as posições dos números
		for (int i = 1; i <= maior; i++) {
			vetorAuxiliar[i] += vetorAuxiliar[i - 1];
		}

		// inicializa arranjo para guardar elementos ordenados
		int[] vetorOrdenado = new int[arranjo.length];

		// lê arranjo original e armazena no novo vetor segundo nova posição
		for (int i = arranjo.length - 1; i >= 0; i--) {
			vetorOrdenado[vetorAuxiliar[arranjo[i]] - 1] = arranjo[i];
			vetorAuxiliar[arranjo[i]]--; // decrementa ocorrência
		}

		// copiando valores ordenados
		for (int i = 0; i < arranjo.length; i++) {
			arranjo[i] = vetorOrdenado[i];
		}
	}
	
	public static void countingSortForRadix(int[] arranjo, int maior, int casa) {
		// inicializa vetor auxiliar com tamanho igual ao maior do arranjo
		int[] vetorAuxiliar = new int[maior + 1];

		// conta as ocorrências dos números segundo a casa decimal da vez 
		// e salva no vetor auxiliar
		for (int i = 0; i < arranjo.length; i++) {
			vetorAuxiliar[(arranjo[i] / casa) % 10]++;
		}

		// marca as posições dos números com custo cumulativo
		for (int i = 1; i <= maior; i++) {
			vetorAuxiliar[i] += vetorAuxiliar[i - 1];
		}

		// inicializa arranjo para guardar elementos ordenados
		int[] vetorOrdenado = new int[arranjo.length];

		// lê arranjo original e armazena no novo vetor segundo a nova posição
		// pela casa decimal da vez
		for (int i = arranjo.length - 1; i >= 0; i--) {
			vetorOrdenado[vetorAuxiliar[(arranjo[i] / casa) % 10] - 1] = arranjo[i];
			vetorAuxiliar[(arranjo[i] / casa) % 10]--; // decrementa ocorrência
		}

		// copiando valores ordenados
		for (int i = 0; i < arranjo.length; i++) {
			arranjo[i] = vetorOrdenado[i];
		}
	}
	
	public static void radixSort(int[] arranjo, int maior) {
		for (int casa = 1; maior / casa > 0; casa *= 10) {
			countingSortForRadix(arranjo, maior, casa);
		}
	}
	
	public static void bucketSort(int[] arranjo, int quantidadeBaldes) {
		// criando um arranjo de baldes que crescem dinamicamente
		ArrayList<Integer>[] baldes = new ArrayList[quantidadeBaldes];
		
		// inicializando os baldes
		for (int i = 0; i < quantidadeBaldes; i++) {
			baldes[i] = new ArrayList<Integer>();
		}
		
		// encontre o lugar do elemento e coloque no balde
		// decidi que os meus elementos só vão de 0 a 99 e 
		// quero distribuir os elementos em 10 baldes, então
		// para saber qual o balde do elemento, basta: 
		// elemento / 100 -> parte inteira
		for (int i = 0; i < arranjo.length; i++) {
			int baldeDoElemento = (int) arranjo[i] / 100;
			baldes[baldeDoElemento].add(arranjo[i]);
		}
		
		// Ordenando cada balde com um algoritmno intermediário
		for (int i = 0; i < quantidadeBaldes; i++) {
			Collections.sort(baldes[i]);
		}
		
		// Concatenando o conteúdo dos baldes no arranjo original
		for (int i = 0, index = 0; i < quantidadeBaldes; i++) {
			for (int j = 0; j < baldes[i].size(); j++, index++) {
				arranjo[index] = baldes[i].get(j);
			}
		}
		
	}
}
