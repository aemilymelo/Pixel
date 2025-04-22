package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.TipoDado;
import br.edu.utfpr.api.service.TipoDadoService;
import br.edu.utfpr.api.utils.ViewImpl;

public class TipoDadoController extends ViewImpl<TipoDado, Long> {

    public TipoDadoController(TipoDadoService service) {
        super(service);
    }

}
