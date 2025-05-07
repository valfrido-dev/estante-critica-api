// Seleciona (ou cria) a database desejada
db = db.getSiblingDB("estantedb");

// Cria as collections desejadas
db.createCollection("users");
db.createCollection("books");

console.log("collection users created!!");
console.log("collection books created!!")

// Você pode inserir documentos iniciais, se necessário
// Inserção de book inicial para testes no desenvolvimento da aplicação
let insertedBooksTest = db.books.insertMany([{
    title: "O dragão renascido",
    subtitle: "Série a roda do tempo vol. 3",
    authors: [ "Robert Jordan" ],
    category: "fantasy",
    publisher: "Editora Intrinseca",
    publicationDate: "10/2014",
    synopsis: `
               Série de livros que deu origem à superprodução A Roda do Tempo, do Amazon Prime Video Na mitologia da série
               A Roda do Tempo, de Robert Jordan, o tempo é uma roda com sete braços, cada um representando uma Era.
               Conforme a roda gira, as Eras vêm e vão, deixando lembranças que desvanecem e se tornam lendas.
               Como a que diz que, quando as forças do Tenebroso, poderosíssimo ente do mal, se reerguerem,
               o poder de combatê-las renascerá em um único homem, o Dragão, que poderá derrotar as forças das sombras em definitivo.
               Ao final de O Olho do Mundo, primeiro livro da série, o jovem Rand descobre que é capaz de manipular
               o poder da Roda do Tempo e que ele talvez seja a reencarnação do Dragão. Agora, no terceiro volume da saga,
               Rand decide que chegou a hora de seguir seu caminho e descobrir se ele é realmente o Dragão Renascido.
               Para tanto, vai à cidade de Tear, a fim de testar uma das antigas profecias: caso ele consiga empunhar Callandor,
               a Espada Que Não Pode Ser Tocada, terá provas de que é a reencarnação de Lews Therin Telamon.
               Já Mat é levado para Tar Valon, onde precisa ser Curado, mas a presença de feiticeiras infiltradas que
               servem às forças do Tenebroso põe todos em perigo. Enquanto isso, Perrin e Moiraine partem para Tear,
               em busca de Rand, na esperança de que ele aceite orientação da feiticeira.
               Fonte: Editor
               `,
    thumbnail: "http://books.google.com/books/content?id=zZ2lBAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
    numberAverageRating: 0,
    createDate: new Date(),
    updateDate: new Date()
    },
    {
        title: "O Pequeno Príncipe (coleção Clássicos Da Literatura Mundial)",
        authors: [ "Antoine De Saint-exupéry", "Jamila Mafra" ],
        category: "fantasy",
        publisher: "Clube de Autores",
        publicationDate: "09/2021",
        synopsis: `
                   Palavras de amor e sabedoria que cativam o coração e a alma fazem parte deste clássico que
                   já conquistou milhões de leitores em todo o mundo. Durante a sua jornada por planetas diferentes
                   e também pelo planeta Terra, o Pequeno Príncipe nos ensina lições valiosas sobre as escolhas,
                   relações humanas e sentimentos como a solidão, a saudade, a amizade e a distância.
                   Já há mais de sete décadas encantando as gerações de leitores, O Pequeno Príncipe é publicado
                   agora pela Mafra Editorial, nesta edição especial, que conta com as ilustrações originais
                   do autor e também com ilustrações inéditas. Esta é considerada uma das maiores obras literárias
                   do século XX, sendo um dos livros mais traduzidos do mundo, para cerca de 253 idiomas.
                   Foi publicada pela primeira vez no ano de 1942 nos Estados Unidos e, três anos depois, na França.
                   Biografia do autor Antoine de Saint-Exupéry Antoine de Saint-Exupéry nasceu em uma
                   família aristocrática, na data de 29 de junho de 1900, na cidade de Lyon, França.
                   Escreveu romances de sucesso como Courrier sud (1929) e Vol de nuit (1931),
                   obtendo grande êxito com o segundo romance, recebendo o Prêmio Fémina. Ele se tornou piloto de
                   avião durante o serviço militar, tendo sido contratado pela Aéropostale. No início da Segunda Guerra
                   Mundial, Antoine de Saint-Exupéry foi mobilizado pela Força Aérea para realizar voos de reconhecimento.
                   Em 17 de junho de 1944, o autor desapareceu no mar depois de um voo para Córsega, seria
                   um voo para uma missão de localização. Saint-Exupéry foi declarado oficialmente morte pela
                   França no ano de 1948. Em 1998 foi encontrada sua pulseira, esclarecendo as causas de sua morte,
                   seu avião havia sido abatido por uma aeronave da alemã.
                   Fonte: Editor
                   `,
        thumbnail: "http://books.google.com/books/content?id=1D9KEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
        numberAverageRating: 0,
        createDate: new Date(),
        updateDate: new Date()
    }]);

if (insertedBooksTest.acknowledged) {
    console.log("Dados iniciais carregados: ", insertedBooksTest.insertedIds);
};
