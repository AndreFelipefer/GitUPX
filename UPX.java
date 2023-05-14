
public class UPX {

    public static void main(String[] args) {
        //Configuração de Distancia Mínima em centimetros
        const int distancia_carro = 10;
        //Configurações de Portas do Arduino

        //Sensor
        const int TRIG = 3;
        const int ECHO = 2;
        //Demais componentes
        const int ledGreen = 7; // Led Verde
        
        const int ledRed = 8; // Lerde Vermelho
        
        const int buzzer = 9;  // Aparelho Sonoro 

// Variaveis para funcionamento do Buzzer
        float seno;  // declara uma variável chamada "seno" com o tipo de dado float. O tipo de dado float é usado para armazenar valores de números decimais de ponto flutuante.
        int frequencia; // declara uma variável chamada "frequencia" com o tipo de dado int. O tipo de dado int é usado para armazenar valores de números inteiros.

        void setup
        
            () { //Essa função é chamada uma vez quando o Arduino é inicializado e é usada para configurar o ambiente de trabalho antes que o programa comece a ser executado.
  Serial.begin(9600);  //A comunicação serial permite que o Arduino se comunique com outros dispositivos ou com um computador, enviando e recebendo dados por meio da porta serial.

            // Configurações do Sensor
            pinMode(TRIG, OUTPUT); //define o pino TRIG como saída, ou seja, configurado para enviar sinais de saída.
            pinMode(ECHO, INPUT); //define o pino ECHO como entrada, ou seja, configurado para receber sinais de entrada.

            // Configurações do LED
            pinMode(ledGreen, OUTPUT); //define o pino ledGreen como saída, ou seja, configurado para enviar sinais de saída.
            pinMode(ledRed, OUTPUT); //define o pino ledRed como saída, ou seja, configurado para enviar sinais de saída.

            //Configurações do Buzzer
            pinMode(buzzer, OUTPUT); //define o pino buzzer como saída, ou seja, configurado para enviar sinais de saída.

        }

        void loop
        
            () { //é responsável por executar o código repetidamente em um loop infinito.
  int distancia = sensor_morcego(TRIG, ECHO); //declara uma variável "distancia" do tipo int e atribui a ela o valor retornado pela função "sensor_morcego(TRIG,ECHO)".

            //O if verifica se a distância é menor ou igual à distância do carro. 
            //Se for verdadeiro, são executadas ações como imprimir uma mensagem de atenção, alterar o estado dos LEDs e acionar o buzzer.
            if (distancia <= distancia_carro) {
                Serial.print("Atenção: ");
                Serial.print(distancia);
                Serial.println("cm");
                digitalWrite(ledGreen, LOW);
                digitalWrite(ledRed, HIGH);
                tocaBuzzer();
            } //O else é executado quando a condição do if não é satisfeita. 
            //Nesse caso, ele imprime uma mensagem indicando que a vaga está livre, altera o estado dos LEDs e para o som do buzzer.
            else {
                Serial.print("Livre: ");
                Serial.print(distancia);
                Serial.println("cm");
                digitalWrite(ledGreen, HIGH);
                digitalWrite(ledRed, LOW);
                noTone(buzzer);
            }
            delay(100); //cria uma pausa de 100 milissegundos antes de continuar a execução do programa.

        }
//A função "sensor_morcego" realiza o seguinte processo:

//Define o pino de trig do sensor como baixo (LOW).
//Aguarda um pequeno intervalo de tempo de 2 microssegundos.
//Define o pino de trig do sensor como alto (HIGH).
//Aguarda um pequeno intervalo de tempo de 10 microssegundos.
//Define o pino de trig do sensor como baixo (LOW) novamente.
//Retorna a medida da distância obtida pelo sensor, calculada usando a função pulseIn e dividida por 58.
        int sensor_morcego
        (int pinotrig,
        int pinoecho
        
            ){
  digitalWrite(pinotrig, LOW);
            delayMicroseconds(2);
            digitalWrite(pinotrig, HIGH);
            delayMicroseconds(10);
            digitalWrite(pinotrig, LOW);

            return pulseIn(pinoecho, HIGH) / 58;
        }

//Função para execução do Alarme Sonoro
//Itera de 0 a 179 em uma variável x.
//Calcula o valor do seno para cada iteração usando a fórmula seno(x * 3.1416 / 180).
//Calcula a frequência adicionando 2000 ao valor do seno multiplicado por 1000.
//Emite um som com a frequência calculada no buzzer usando a função "tone".
//Aguarda um pequeno intervalo de tempo de 2 milissegundos antes de continuar para a próxima iteração.
        void tocaBuzzer
        
            (){
 for (int x = 0; x < 180; x++) {
                seno = (sin(x * 3.1416 / 180));
                frequencia = 2000 + (int(seno * 1000
                ));
  tone(buzzer, frequencia);
                delay(2);
            }

        }

    }

}
