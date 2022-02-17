package es.trident.rotomnet.model;

public class PokemonCard {
	int _number;
	boolean _owned;
	boolean _shiny;
	
	public PokemonCard(int number, boolean owned, boolean shiny) {
		_number = number;
		_owned = owned;
		_shiny = shiny;
	}

	public int getNumber() {
		return _number;
	}

	public void setNumber(int _number) {
		this._number = _number;
	}

	public boolean isOwned() {
		return _owned;
	}

	public void setOwned(boolean _owned) {
		this._owned = _owned;
	}

	public boolean isShiny() {
		return _shiny;
	}

	public void setShiny(boolean _shiny) {
		this._shiny = _shiny;
	}

}
