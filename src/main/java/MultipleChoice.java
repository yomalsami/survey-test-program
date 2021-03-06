import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


// Start of user code (user defined imports)

// End of user code

/**
 * Description of Multiple Choice.
 * 
 * @author phillipryan
 */
public class MultipleChoice extends Question {
	
	protected MultipleChoice(Car car) {
		super(car);
	}

	/**
	 * Description of the method Create.
	 */
	public void Create() {
		io.Output("Enter the prompt for your Multiple Choice question");
		String Prompt = io.getInputString();
		prompt = Prompt;
		
		while(numberOfChoices == 0){
			io.Output("Enter the number of choices:");
			numberOfChoices = io.getInput();
			numberOfCorrectChoices = 1;
		}
		
		for(int i=0; i<numberOfChoices; i++){
			io.Output("Enter choice " + (i+1));
			String Choice = io.getInputString();
			response.setChoices(Choice);
		}
	}
	
	@Override
	protected void CreateCA() {
		while(numberOfCorrectChoices == 0){
			io.Output("Enter the number of correct choices:");
			numberOfCorrectChoices = io.getInput();
		}
		
		for(int i=0; i<numberOfCorrectChoices; i++){
			io.Output("Enter correct choice");
			String Correct = io.getInputString();
			response.setCorrectAnswer(Correct);
		}
	}
	 
	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.Output(this.prompt);
	}
	 
	/**
	 * Description of the method Edit.
	 */
	public void Edit(int type) {
		if( type == 1 ){
			io.Output("Enter the prompt for your question");
			String Prompt = io.getInputString();
			prompt = Prompt;
		}
		else if( type == 2 ){
			int difference = 0, question = 0, newNumChoices = 0;
			while(newNumChoices == 0){
				io.Output("Enter the number of choices: ");
				newNumChoices = io.getInput();
				difference  = newNumChoices - numberOfChoices;
				numberOfChoices = newNumChoices;
			}
			
			for(int i=0; i<difference; i++){
				io.Output("You choose a number of choices greater then your current number of choices");
				io.Output("Enter the choice you would like to add for your question: ");
				String resp = io.getInputString();
				response.setChoices(resp);
			}
			
			boolean cont = true;
			while(cont){
				io.Output("Would you like to edit an existing choice?");
				String Choice = io.getInputString();
				cont = io.interpretResponse(Choice);
				if(cont){
					response.displayChoices();
					int sizeOfChoices = response.lengthChoices();
					while(question == 0 && question <= sizeOfChoices){
						io.Output("Enter the choice to modify: ");
						question = io.getInput();
					}
					io.Output("Enter the new choice for your question");
					Choice = io.getInputString();
					response.editChoice(question-1, Choice);
					question = 0;
				}
			}
		}
		else if( type == 3 ){
			int difference = 0, question = 0, newNumCA = 0;
			while(newNumCA == 0){
				io.Output("Enter the number of correct answers: ");
				newNumCA = io.getInput();
				difference  = newNumCA - numberOfCorrectChoices;
			}
			
			for(int i=0; i<difference; i++){
				io.Output("You choose a number of correct answers greater then your current number of correct answers");
				response.displayChoices();
				io.Output("Choose the correct answer you would like to add to your question: ");
				String resp = io.getInputString();
				response.setCorrectAnswer(resp);
			}
			
			boolean cont = true;
			while(cont){
				io.Output("Would you like to edit an existing correct answer?");
				String Choice = io.getInputString();
				cont = io.interpretResponse(Choice);
				if(cont){
					response.getCA();
					int sizeOfCA = response.lengthCA();
					while(question == 0 && question <= sizeOfCA){
						io.Output("Enter the correct answer to modify: ");
						question = io.getInput();
					}
					io.Output("Enter the new correct answer for your question");
					Choice = io.getInputString();
					response.editChoice(question-1, Choice);
				}
			}
		}
		else{
			io.Output("Error not a recognized option");
		}
	}
	 
	/**
	 * Description of the method Take.
	 */
	public void Take() {
		for(int i=0; i<numberOfCorrectChoices; i++){
			io.Output1("Enter a number: ");
			String Answer = io.getInputString();
			response.setResponse(Answer);
		}
	}

	@Override
	protected String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	protected void setUserName(String newUserName) {
		// TODO Auto-generated method stub
		userName = newUserName;
	}

	@Override
	protected Car getResponse() {
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	protected String getPrompt() {
		// TODO Auto-generated method stub
		return prompt;
	}

	@Override
	protected void setPrompt(String newPrompt) {
		// TODO Auto-generated method stub
		prompt = newPrompt;
	}
}
