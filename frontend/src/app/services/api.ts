import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Api {

  // =========================================================
  // URL DOS GETS
  // =========================================================
  // ApiController:
  // @RequestMapping("/api")

  private urlApi = 'http://localhost:8080/api';


  // =========================================================
  // URL DOS POSTS
  // =========================================================
  // IntegranteController -> /integrantes
  // TimeController -> /Times

  private urlCadastro = 'http://localhost:8080';


  constructor(private http: HttpClient) {}


  // =========================================================
  // GET 1 - TIME DA DATA
  // =========================================================

  buscarTimeDaData(data: string) {

    return this.http.get<{
      data: string;
      clube: string;
      integrantes: string[];
    }>(
      `${this.urlApi}/${data}`
    );

  }


  // =========================================================
  // GET 2 - INTEGRANTE MAIS USADO
  // =========================================================

  integranteMaisUsado(
    dataInicial: string,
    dataFinal: string
  ) {

    return this.http.get<{
      nome: string;
      funcao: string;
    }>(
      `${this.urlApi}/integrante-mais-usado`,
      {
        params: {
          dataInicial,
          dataFinal
        }
      }
    );

  }


  // =========================================================
  // GET 3 - INTEGRANTES DO TIME MAIS RECORRENTE
  // =========================================================

  integrantesDoTimeMaisRecorrente(
    dataInicial: string,
    dataFinal: string
  ) {

    return this.http.get<{
      integrantes: string[];
    }>(
      `${this.urlApi}/integrantes-do-time-mais-recorrente`,
      {
        params: {
          dataInicial,
          dataFinal
        }
      }
    );

  }


  // =========================================================
  // GET 4 - FUNÇÃO MAIS RECORRENTE
  // =========================================================

  funcaoMaisRecorrente(
    dataInicial: string,
    dataFinal: string
  ) {

    return this.http.get<{
      funcao: string;
    }>(
      `${this.urlApi}/funcao-mais-recorrente`,
      {
        params: {
          dataInicial,
          dataFinal
        }
      }
    );

  }


  // =========================================================
  // GET 5 - CLUBE MAIS RECORRENTE
  // =========================================================

  clubeMaisRecorrente(
    dataInicial: string,
    dataFinal: string
  ) {

    return this.http.get<{
      clube: string;
    }>(
      `${this.urlApi}/clube-mais-recorrente`,
      {
        params: {
          dataInicial,
          dataFinal
        }
      }
    );

  }


  // =========================================================
  // GET 6 - CONTAGEM DE CLUBES
  // =========================================================

  contagemDeClubes(
    dataInicial: string,
    dataFinal: string
  ) {

    return this.http.get<{
      contagemDeClubesNoPeriodo: {
        [nome: string]: number;
      };
    }>(
      `${this.urlApi}/contagem-de-clubes`,
      {
        params: {
          dataInicial,
          dataFinal
        }
      }
    );

  }


  // =========================================================
  // GET 7 - CONTAGEM POR FUNÇÃO
  // =========================================================

  contagemPorFuncao(
    dataInicial: string,
    dataFinal: string
  ) {

    return this.http.get<{
      contagemPorFuncao: {
        [nome: string]: number;
      };
    }>(
      `${this.urlApi}/contagem-por-funcao`,
      {
        params: {
          dataInicial,
          dataFinal
        }
      }
    );

  }


  // =========================================================
  // POST - CADASTRAR INTEGRANTE
  // =========================================================
  // Controller:
  // @RequestMapping("/integrantes")
  // @PostMapping
  //
  // URL:
  // http://localhost:8080/integrantes

  cadastrarIntegrante(integrante: any) {

    return this.http.post(
      `${this.urlCadastro}/integrantes`,
      integrante
    );

  }


  // =========================================================
  // POST - CADASTRAR TIME
  // =========================================================
  // Controller:
  // @RequestMapping("/Times")
  // @PostMapping
  //
  // URL:
  // http://localhost:8080/Times

  cadastrarTime(time: any) {

    return this.http.post(
      `${this.urlCadastro}/Times`,
      time
    );

  }

}
