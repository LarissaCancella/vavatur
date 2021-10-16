# vavatur
projeto faeterj prj

Realizar Checking

Fluxo principal
A1. O sistema exibe a tela inicial que exibe uma opção “realizar checking”.
A2. O usuário seleciona a opção “realizar checking” no sistema.
A3. O sistema solicita o código localizador do bilhete.
A4. O usuário informa o código localizador e seleciona “próximo”.
A5. O sistema localiza o bilhete.
A6. O sistema exibe as informações do bilhete, como origem, destino, hora prevista do
embarque, hora prevista da partida, a hora do relógio da parede (para o usuário se
planejar); as informações do passageiro, que no caso, são nome e cpf; e as opções:
“atualizar dados pessoais” e “próximo”.
A7. O usuario seleciona “próximo”.
A8. O sistema exibe uma tela para marcação de assento da linha em questão posicionados de
acordo com o tipo do veículo que efetuará a passagem e indicando visualmente os assentos
disponíveis e os não disponíveis para marcação.
A9. O usuario seleciona o seu assento e seleciona “próximo’.
A10. O sistema marca o assento para esse localizador do bilhete.
A11. O sistema exibe a mensagem “Checking realizado com sucesso”.
A12. O sistema volta para a tela inicial.
Fluxos alternativos
B5. O sistema não localiza o bilhete.
B6. O sistema informa que o bilhete não foi localizado.
[retorna ao A1]
C7. O usuário seleciona “atualizar dados pessoais”.
C8. O sistema exibe campos para edição do nome e do cpf do passageiro com os seus respectivos
valores atuais e as opções “salvar alterações” e “voltar”.
C9. O usuario altera o nome e/ou o cpf e seleciona “salvar alterações”.
C10. O sistema grava as novas informações pessoais.
[retorna ao item A6]
D10. O sistema não consegue marcar o assento.
D11. O sistema exibe uma mensagem informando que não foi possível efetuar o checking.
[retorna ao item A1]
