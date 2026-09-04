package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class TesteApiService {

    private final static LocalDate data1993 = LocalDate.of(1993,1, 1);
    private final static LocalDate data1994 = LocalDate.of(1994,1, 1);
    private final static LocalDate data1995 = LocalDate.of(1995,1, 1);

    @Spy
    private ApiService apiService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @DataProvider
    public static Object[][] testTimeDaDataParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Time timeChicagoBullsDe1995 = dadosParaTesteApiService.getTimeChicagoBullsDe1995();
        Time timeDetroidPistonsDe1993 = dadosParaTesteApiService.getTimeDetroidPistonsDe1993();

        return new Object[][]{
                {
                        data1995,
                        todosOsTimes,
                        timeChicagoBullsDe1995
                },
                {
                        data1993,
                        todosOsTimes,
                        timeDetroidPistonsDe1993
                }
        };
    }

    @Test
    @UseDataProvider("testTimeDaDataParams")
    public void testTimeDaData(LocalDate data, List<Time> todosOsTimes, Time esperado) {

        Time timeRetornado = apiService.timeDaData(data, todosOsTimes);

        assertEquals(esperado, timeRetornado);
    }



    @DataProvider
    public static Object[][] testIntegranteMaisUsadoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();

        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dadosParaTesteApiService.getDenis_rodman()
                }
        };
    }


    @Test
    @UseDataProvider("testIntegranteMaisUsadoParams")
    public void testIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Integrante esperado) {

        Integrante integranteRetornado = apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, integranteRetornado);
    }



    @DataProvider
    public static Object[][] testTimeMaisRecorrenteParams() {
        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        List<String> integrantesEsperados = Arrays.asList(
                dadosParaTesteApiService.getDenis_rodman().getNome(),
                dadosParaTesteApiService.getMichael_jordan().getNome(),
                dadosParaTesteApiService.getScottie_pippen().getNome()
        );
        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        integrantesEsperados
                }
        };
    }

    @Test
    @UseDataProvider("testTimeMaisRecorrenteParams")
    public void testIntegrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, List<String> esperado) {

        List<String> nomeDosIntegrantesDoTimeMaisRecorrente = apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);

        if(nomeDosIntegrantesDoTimeMaisRecorrente != null){
            nomeDosIntegrantesDoTimeMaisRecorrente.sort(Comparator.naturalOrder());
        }

        assertEquals(esperado, nomeDosIntegrantesDoTimeMaisRecorrente);
    }



    @DataProvider
    public static Object[][] testFuncaoMaisRecorrenteParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        "ala"
                }
        };
    }

    @Test
    @UseDataProvider("testFuncaoMaisRecorrenteParams")
    public void testFuncaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado) {

        String funcaoMaisRecorrente = apiService.funcaoMaisRecorrente(dataInicial, dataFinal, todosOsTimes);

        assertEquals(esperado, funcaoMaisRecorrente);
    }

    @DataProvider
    public static Object[][] testClubeMaisRecorrenteParams() {
        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        dadosParaTesteApiService.getClubeChicagoBulls()
                }
        };
    }

    @Test
    @UseDataProvider("testClubeMaisRecorrenteParams")
    public void testClubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, String esperado) {

        String clubeMaisRecorrente = apiService.clubeMaisRecorrente(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, clubeMaisRecorrente);
    }

    @DataProvider
    public static Object[][] testContagemDeClubesParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado1 = new HashMap<>();
        esperado1.put(dadosParaTesteApiService.getClubeDetroitPistons(), 1L);
        esperado1.put(dadosParaTesteApiService.getClubeChicagoBulls(), 2L);

        Map<String, Long> esperado2 = new HashMap<>();
        esperado2.put(dadosParaTesteApiService.getClubeChicagoBulls(), 2L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado1
                },
                {
                        data1994,
                        data1995,
                        todosOsTimes,
                        esperado2
                }
        };
    }

    @Test
    @UseDataProvider("testContagemDeClubesParams")
    public void testcontagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemDeClubesNoPeriodo = apiService.contagemDeClubesNoPeriodo(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemDeClubesNoPeriodo);
    }



    @DataProvider
    public static Object[][] testContagemPorFuncaoParams() {

        DadosParaTesteApiService dadosParaTesteApiService = new DadosParaTesteApiService();
        List<Time> todosOsTimes = dadosParaTesteApiService.getTodosOsTimes();

        Map<String, Long> esperado = new HashMap<>();
        esperado.put("ala", 2L);
        esperado.put("ala-pivô", 1L);

        return new Object[][]{
                {
                        data1993,
                        data1995,
                        todosOsTimes,
                        esperado
                }
        };
    }

    @Test
    @UseDataProvider("testContagemPorFuncaoParams")
    public void testContagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes, Map<String, Long> esperado) {

        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);
        assertEquals(esperado, contagemPorFuncao);
    }

}
