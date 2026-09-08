import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Api } from './services/api';

// =========================================================
// MODELOS
// =========================================================

// Retorno do GET de time por data
interface TimeDaData {
  data: string;
  clube: string;
  integrantes: string[];
}

// Retorno do integrante mais usado
interface IntegranteMaisUsado {
  nome: string;
  funcao: string;
}

// Retorno da contagem de clubes
interface ContagemClubesResponse {
  contagemDeClubesNoPeriodo: {
    [nome: string]: number;
  };
}

// Retorno da contagem por função
interface ContagemFuncoesResponse {
  contagemPorFuncao: {
    [nome: string]: number;
  };
}

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  // =========================================================
  // TELA ATUAL
  // =========================================================

  tela = signal<'dashboard' | 'cadastro'>('dashboard');


  // =========================================================
  // TIME DA DATA
  // =========================================================

  data = '';

  time = signal<TimeDaData | null>(null);


  // =========================================================
  // FILTRO DE PERÍODO
  // =========================================================

  dataInicial = '';

  dataFinal = '';


  // =========================================================
  // RESULTADOS DO DASHBOARD
  // =========================================================

  integrante =
    signal<IntegranteMaisUsado | null>(null);

  integrantesTime =
    signal<string[]>([]);

  funcao =
    signal<string>('');

  clube =
    signal<string>('');


  // =========================================================
  // CONTAGEM DE CLUBES
  // =========================================================

  contagemClubes =
    signal<{ nome: string; quantidade: number }[]>([]);


  // =========================================================
  // CONTAGEM POR FUNÇÃO
  // =========================================================

  contagemFuncoes =
    signal<{ nome: string; quantidade: number }[]>([]);


  // =========================================================
  // CADASTRO DE INTEGRANTE
  // =========================================================

  novoIntegrante = {
    nome: '',
    funcao: ''
  };


  // =========================================================
  // CADASTRO DE TIME
  // =========================================================

  novoTime = {
    nomeDoClube: '',
    data: '',
    integrantesIds: [] as number[]
  };


  // Campo para digitar os IDs
  idsIntegrantes = '';


  // =========================================================
  // CONSTRUTOR
  // =========================================================

  constructor(private api: Api) {}


  // =========================================================
  // NAVEGAÇÃO
  // =========================================================

  abrirDashboard() {
    this.tela.set('dashboard');
  }

  abrirCadastro() {
    this.tela.set('cadastro');
  }


  // =========================================================
  // GET 1 - TIME DA DATA
  // =========================================================

  buscar() {

    this.api.buscarTimeDaData(this.data)
      .subscribe({

        next: response => {

          console.log(
            'Time da data:',
            response
          );

          this.time.set(response);
        },

        error: error => {

          console.error(
            'Erro ao buscar time:',
            error
          );

          this.time.set(null);

          alert(
            'Nenhum time encontrado para essa data.'
          );
        }

      });
  }


  // =========================================================
  // GET 2 - INTEGRANTE MAIS USADO
  // =========================================================

  buscarIntegranteMaisUsado() {

    this.api.integranteMaisUsado(
      this.dataInicial,
      this.dataFinal
    )
      .subscribe({

        next: response => {

          console.log(
            'Integrante mais usado:',
            response
          );

          this.integrante.set(response);
        },

        error: error => {

          console.error(
            'Erro:',
            error
          );

          this.integrante.set(null);
        }

      });
  }


  // =========================================================
  // GET 3 - INTEGRANTES DO TIME MAIS RECORRENTE
  // =========================================================

  buscarIntegrantesTimeMaisRecorrente() {

    this.api.integrantesDoTimeMaisRecorrente(
      this.dataInicial,
      this.dataFinal
    )
      .subscribe({

        next: response => {

          console.log(
            'Integrantes do time mais recorrente:',
            response
          );

          this.integrantesTime.set(
            response.integrantes
          );
        },

        error: error => {

          console.error(
            'Erro:',
            error
          );

          this.integrantesTime.set([]);
        }

      });
  }


  // =========================================================
  // GET 4 - FUNÇÃO MAIS RECORRENTE
  // =========================================================

  buscarFuncaoMaisRecorrente() {

    this.api.funcaoMaisRecorrente(
      this.dataInicial,
      this.dataFinal
    )
      .subscribe({

        next: response => {

          console.log(
            'Função mais recorrente:',
            response
          );

          this.funcao.set(
            response.funcao
          );
        },

        error: error => {

          console.error(
            'Erro:',
            error
          );

          this.funcao.set('');
        }

      });
  }


  // =========================================================
  // GET 5 - CLUBE MAIS RECORRENTE
  // =========================================================

  buscarClubeMaisRecorrente() {

    this.api.clubeMaisRecorrente(
      this.dataInicial,
      this.dataFinal
    )
      .subscribe({

        next: response => {

          console.log(
            'Clube mais recorrente:',
            response
          );

          this.clube.set(
            response.clube
          );
        },

        error: error => {

          console.error(
            'Erro:',
            error
          );

          this.clube.set('');
        }

      });
  }


  // =========================================================
  // GET 6 - CONTAGEM DE CLUBES
  // =========================================================

  buscarContagemClubes() {

    this.api.contagemDeClubes(
      this.dataInicial,
      this.dataFinal
    )
      .subscribe({

        next: (response: ContagemClubesResponse) => {

          console.log(
            'Contagem de clubes:',
            response
          );

          const resultado =
            Object.entries(
              response.contagemDeClubesNoPeriodo
            )
            .map(([nome, quantidade]) => ({
              nome,
              quantidade
            }));

          this.contagemClubes.set(resultado);
        },

        error: error => {

          console.error(
            'Erro:',
            error
          );

          this.contagemClubes.set([]);
        }

      });
  }


  // =========================================================
  // GET 7 - CONTAGEM POR FUNÇÃO
  // =========================================================

  buscarContagemPorFuncao() {

    this.api.contagemPorFuncao(
      this.dataInicial,
      this.dataFinal
    )
      .subscribe({

        next: (response: ContagemFuncoesResponse) => {

          console.log(
            'Contagem por função:',
            response
          );

          const resultado =
            Object.entries(
              response.contagemPorFuncao
            )
            .map(([nome, quantidade]) => ({
              nome,
              quantidade
            }));

          this.contagemFuncoes.set(resultado);
        },

        error: error => {

          console.error(
            'Erro:',
            error
          );

          this.contagemFuncoes.set([]);
        }

      });
  }


  // =========================================================
  // POST - CADASTRAR INTEGRANTE
  // =========================================================

  cadastrarIntegrante() {

    this.api.cadastrarIntegrante(
      this.novoIntegrante
    )
      .subscribe({

        next: response => {

          console.log(
            'Integrante cadastrado:',
            response
          );

          alert(
            'Integrante cadastrado com sucesso!'
          );

          this.novoIntegrante = {
            nome: '',
            funcao: ''
          };
        },

        error: error => {

          console.error(
            'Erro ao cadastrar integrante:',
            error
          );

          alert(
            'Erro ao cadastrar integrante.'
          );
        }

      });
  }


  // =========================================================
  // ADICIONAR INTEGRANTES AO TIME
  // =========================================================

  adicionarIntegrantes() {

    const ids = this.idsIntegrantes
      .split(',')
      .map(id => Number(id.trim()))
      .filter(id => !isNaN(id) && id > 0);

    this.novoTime.integrantesIds = ids;

    console.log(
      'Integrantes selecionados:',
      this.novoTime.integrantesIds
    );
  }


  // =========================================================
  // POST - CADASTRAR TIME
  // =========================================================

  cadastrarTime() {

    console.log(
      'Time enviado:',
      this.novoTime
    );

    this.api.cadastrarTime(
      this.novoTime
    )
      .subscribe({

        next: response => {

          console.log(
            'Time cadastrado:',
            response
          );

          alert(
            'Time cadastrado com sucesso!'
          );

          this.novoTime = {
            nomeDoClube: '',
            data: '',
            integrantesIds: []
          };

          this.idsIntegrantes = '';
        },

        error: error => {

          console.error(
            'Erro ao cadastrar time:',
            error
          );

          alert(
            'Erro ao cadastrar time.'
          );
        }

      });
  }

}
