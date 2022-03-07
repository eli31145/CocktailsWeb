export interface User {
  user: string
  email: string
  cid?: number

}

export interface Cocktail {
  idDrink: string
  strDrink: string
  strAlcoholic?: string
  strGlass?: string
  strInstructions?: string
//reference mod3 assessment
  strDrinkThumb: string
//comment out ingredients & measures
  /* strIngredients: String[] */
  /* strMeasures: String[] */
  ingredientAndMeasure?: string[]

}

export interface CocktailSummary {
  email: string
  //possibly in String []
  idDrink: string
  strDrink?: String
}
