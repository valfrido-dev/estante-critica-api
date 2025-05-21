package com.personal.project.estante_critica_api.consumer.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ApiGoogleBooksConsumerServiceTest {

    @InjectMocks
    private ApiGoogleBooksConsumerService googleBooksService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testSeachGoogleBooksConsumer() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(this.getResponseApi());
        var response = googleBooksService.getApiResponse("O dragão renascido", "Editora Intrinseca");
        assertEquals("http://books.google.com/books/content?id=zZ2lBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api", response);
    }

    private String getResponseApi() {
        return """
                {
                    "kind": "books#volumes",
                    "totalItems": 1,
                    "items": [
                        {
                            "kind": "books#volume",
                            "id": "zZ2lBAAAQBAJ",
                            "etag": "Lcq+TgsB5ao",
                            "selfLink": "https://www.googleapis.com/books/v1/volumes/zZ2lBAAAQBAJ",
                            "volumeInfo": {
                                "title": "O dragão renascido",
                                "subtitle": "Série a roda do tempo vol. 3",
                                "authors": [
                                    "Robert Jordan"
                                ],
                                "publisher": "Editora Intrinseca",
                                "publishedDate": "2014-10-01",
                                "description": "Série de livros que deu origem à superprodução A Roda do Tempo, do Amazon Prime Video Na mitologia da série A Roda do Tempo, de Robert Jordan, o tempo é uma roda com sete braços, cada um representando uma Era. Conforme a roda gira, as Eras vêm e vão, deixando lembranças que desvanecem e se tornam lendas. Como a que diz que, quando as forças do Tenebroso, poderosíssimo ente do mal, se reerguerem, o poder de combatê-las renascerá em um único homem, o Dragão, que poderá derrotar as forças das sombras em definitivo. Ao final de O Olho do Mundo, primeiro livro da série, o jovem Rand descobre que é capaz de manipular o poder da Roda do Tempo e que ele talvez seja a reencarnação do Dragão. Agora, no terceiro volume da saga, Rand decide que chegou a hora de seguir seu caminho e descobrir se ele é realmente o Dragão Renascido. Para tanto, vai à cidade de Tear, a fim de testar uma das antigas profecias: caso ele consiga empunhar Callandor, a Espada Que Não Pode Ser Tocada, terá provas de que é a reencarnação de Lews Therin Telamon. Já Mat é levado para Tar Valon, onde precisa ser Curado, mas a presença de feiticeiras infiltradas que servem às forças do Tenebroso põe todos em perigo. Enquanto isso, Perrin e Moiraine partem para Tear, em busca de Rand, na esperança de que ele aceite orientação da feiticeira.",
                                "industryIdentifiers": [
                                    {
                                        "type": "ISBN_13",
                                        "identifier": "9788580576023"
                                    },
                                    {
                                        "type": "ISBN_10",
                                        "identifier": "8580576024"
                                    }
                                ],
                                "readingModes": {
                                    "text": true,
                                    "image": true
                                },
                                "pageCount": 751,
                                "printType": "BOOK",
                                "categories": [
                                    "Fiction"
                                ],
                                "maturityRating": "NOT_MATURE",
                                "allowAnonLogging": true,
                                "contentVersion": "2.27.26.0.preview.3",
                                "panelizationSummary": {
                                    "containsEpubBubbles": false,
                                    "containsImageBubbles": false
                                },
                                "imageLinks": {
                                    "smallThumbnail": "http://books.google.com/books/content?id=zZ2lBAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                                    "thumbnail": "http://books.google.com/books/content?id=zZ2lBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
                                },
                                "language": "pt-BR",
                                "previewLink": "http://books.google.com/books?id=zZ2lBAAAQBAJ&pg=PT14&dq=search+intitle:O+drag%C3%A3o+renascido+inpublisher:Editora+Intrinseca&hl=&cd=1&source=gbs_api",
                                "infoLink": "https://play.google.com/store/books/details?id=zZ2lBAAAQBAJ&source=gbs_api",
                                "canonicalVolumeLink": "https://play.google.com/store/books/details?id=zZ2lBAAAQBAJ"
                            },
                            "saleInfo": {
                                "country": "US",
                                "saleability": "FOR_SALE",
                                "isEbook": true,
                                "listPrice": {
                                    "amount": 23.99,
                                    "currencyCode": "USD"
                                },
                                "retailPrice": {
                                    "amount": 14.39,
                                    "currencyCode": "USD"
                                },
                                "buyLink": "https://play.google.com/store/books/details?id=zZ2lBAAAQBAJ&rdid=book-zZ2lBAAAQBAJ&rdot=1&source=gbs_api",
                                "offers": [
                                    {
                                        "finskyOfferType": 1,
                                        "listPrice": {
                                            "amountInMicros": 23990000,
                                            "currencyCode": "USD"
                                        },
                                        "retailPrice": {
                                            "amountInMicros": 14390000,
                                            "currencyCode": "USD"
                                        },
                                        "giftable": true
                                    }
                                ]
                            },
                            "accessInfo": {
                                "country": "US",
                                "viewability": "PARTIAL",
                                "embeddable": true,
                                "publicDomain": false,
                                "textToSpeechPermission": "ALLOWED",
                                "epub": {
                                    "isAvailable": true,
                                    "acsTokenLink": "http://books.google.com/books/download/O_drag%C3%A3o_renascido-sample-epub.acsm?id=zZ2lBAAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
                                },
                                "pdf": {
                                    "isAvailable": true,
                                    "acsTokenLink": "http://books.google.com/books/download/O_drag%C3%A3o_renascido-sample-pdf.acsm?id=zZ2lBAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api"
                                },
                                "webReaderLink": "http://play.google.com/books/reader?id=zZ2lBAAAQBAJ&hl=&source=gbs_api",
                                "accessViewStatus": "SAMPLE",
                                "quoteSharingAllowed": false
                            },
                            "searchInfo": {
                                "textSnippet": "... <b>Dragão</b> em algum lugar da Planície de Almoth, outro assolava Saldaea, e um terceiro atingia Tear. Três de uma ... busca de sinais e presságios — quais exatamente, não diziam — e viajavam em navios com metade da tripulação ou mesmo&nbsp;..."
                            }
                        }
                    ]
                }
                """;
    }

}
