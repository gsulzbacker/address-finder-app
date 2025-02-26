# Address Finder

Aplicativo de busca de endereço através do CEP. 

O processo de busca é responsivo: ao completar o input de 8 dígitos, o app faz a requisição para a API e caso o CEP seja válido, os valores dos campos de endereço são atualizados com base no retorno da API. Caso seja inválido, uma mensagem de erro é mostrada ao usuário.

Principais recursos utilizados: 
- Jetpack Compose
- Navigation
- Retrofit
- Koin
- [API: ViaCEP](https://viacep.com.br/)
