It is a Mancala playing agent, which will give their steps calculating all the probable outcomes. We will make different heuristic functions for different machines to compete against each other. 

We will make a class, which will be the competition class, and it will arrange competitions between different types of agents. We will also make a Mancala AI class, which will be responsible for playing and determining the next step for it. The Mancala AI class will contain almost every piece of information about the agent playing. 

While creating the instance for the Mancala AI class, we will start them with the parameter of what type of heuristics we want to calculate. We will use them as the instance set up earlier. In the Mancala AI class, we will need to use nodes for creating a tree-like object. We have to create a separate class for that tree. We can add some necessary information about the node as its elements. 

Also, we have to select W1, W2, W3... values when creating the Mancala AI class. We also have to determine the value of the depth we want to search at one step. We may have to calculate them manually. We also have to tune our AI to find the most effective W values to get. We can do that by iterating and arranging competitions between all of them. It may be more helpful but also time-consuming. 

We have to calculate alpha and beta values of the nodes of the tree. We have to calculate the max values in the alpha node and the min values in the beta node.
