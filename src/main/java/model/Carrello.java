package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Carrello {
	
	ArrayList<ProdottoCarrello> cart;
	
	public Carrello() {
		cart = new ArrayList<>();
	}
	
	public void addProdotto(ProdottoCarrello p) {
		for(ProdottoCarrello pr : cart) {
			if(pr.getId() == p.getId()) {
				pr.qta++;
				return;
			}
		}
		cart.add(p);
	}
	
	public void addProdotto(int id, String nome, String descrizione, int qta, float prezzo, String materiale, String colore) {
		ProdottoCarrello p = new ProdottoCarrello(id, nome, descrizione, qta, prezzo, materiale, colore);
		for(ProdottoCarrello pr : cart) {
			if(pr.getId() == id) {
				pr.qta += qta;
				return;
			}
		}
		cart.add(p);
	}
	
	public void updateProdotto(int id, int qta) {
		for(ProdottoCarrello pr : cart) {
			if(pr.getId() == id) {
				pr.qta = qta;
				return;
			}
		}
	}
	
	public ProdottoCarrello getProdotto(int id) {
		for(ProdottoCarrello p : cart) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<ProdottoCarrello> getCart() {
		return cart;
	}
	
	public int size() {
		return cart.size();
	}
	
	public float getTotalPrice() {
		float somma = 0;
		for(ProdottoCarrello p : cart) {
			somma += p.getPrezzo() * p.getQta();
		}
		return somma;
	}
	
	public void deleteProdotto(int id) {
	    Iterator<ProdottoCarrello> iterator = cart.iterator();
	    while(iterator.hasNext()) {
	        ProdottoCarrello p = iterator.next();
	        if(p.getId() == id) {
	            iterator.remove();
	            break;
	        }
	    }
	}

}